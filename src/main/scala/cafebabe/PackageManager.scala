package cafebabe

class PackageManager(val name: String, packages: Seq[PackageManager] = Seq()) {
  import scala.collection.mutable.ListBuffer
  import java.nio.file.{ Path, Paths }

  val classes = ListBuffer.empty[ClassFile]
  val subPackages = packages.to[ListBuffer]
  val javaName = name.replace('.', '/')

  def addClass(name: String, parentName: Option[String] = None) = {
    val classFile = new ClassFile(s"$javaName/$name", parentName)
    classes += classFile
    classFile
  }

  def containsClass(name: String) = {
    classes exists (_.className == s"$javaName/$name")
  }

  def findClass(name: String) = {
    classes find (_.className == s"$javaName/$name")
  }

  def addSubPackage(name: String, packages: Seq[PackageManager] = Seq()) = {
    val pkg = new PackageManager(s"${this.name}.${name}", packages)
    subPackages += pkg
    pkg
  }

  def writeTo(dir: String): Unit = writeTo(Paths.get(dir))

  def writeTo(dir: Path): Unit = {
    // Convert package name (e.g. com.blah.blah) to path (e.g. com/blah/blah)
    val namePath = {
      if (javaName.contains('/')) {
        val parts = javaName.split('/')
        Paths.get(parts.head, parts.tail: _*)
      } else {
        Paths.get(name)
      }
    }

    val packagePath = dir.resolve(namePath)
    packagePath.toFile.mkdirs

    classes foreach { packageClass =>
      val name = packageClass.className
      val classFileName = name.substring(name.lastIndexOf('/') + 1)
      val path = packagePath.resolve(s"$classFileName.class")
      packageClass.writeToFile(path.toString)
    }

    subPackages foreach (_.writeTo(dir.resolve(name)))
  }
}
