import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

class MyFileVisitor extends SimpleFileVisitor<Path> {
    boolean isFind = false;
    Path nameFile;

    public MyFileVisitor(Path nameFile) {
        this.nameFile = nameFile;
    }

    @Override
    public FileVisitResult visitFileFailed(Path dir, IOException e) throws IOException {
        return FileVisitResult.SKIP_SUBTREE;
    }
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isExecutable(file)) {
            if (file.endsWith(nameFile)) {
                this.nameFile = file;
                this.isFind = true;
                return FileVisitResult.TERMINATE;
            }
            else {
                return FileVisitResult.CONTINUE;
            }
        }
        else {
            return FileVisitResult.SKIP_SUBTREE;
        }
    }
}