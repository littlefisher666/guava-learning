package com.littlefisher.guava.io;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

/**
 * TODO: 可记录
 */
public class FilesTest {

    private static Logger logger = LogManager.getLogger(FilesTest.class);

    /**
     * 一. Guava的文件写入
     */
    @Test
    public void test1() {
        String fileName = "e://a.txt";
        String contents = "hello world";
        checkNotNull(fileName, "Provided file name for writing must NOT be null.");
        checkNotNull(contents, "Unable to write null contents.");
        final File newFile = new File(fileName);
        try {
            Files.write(contents.getBytes(), newFile);
        } catch (IOException fileIoEx) {
            logger.error(
                    "ERROR trying to write to file '" + fileName + "' - " + fileIoEx.toString());
        }
    }

    /**
     * 获取文件内容
     */
    @Test
    public void test2() throws IOException {
        String testFilePath = "E:\\a.txt";
        File testFile = new File(testFilePath);
        List<String> lines = Files.readLines(testFile, Charsets.UTF_8);
        for (String line : lines) {
            logger.debug(line);
        }
    }

    /**
     * 注意这里的readLines方法返回的是List<String>的接口，这对于大文件处理是会有问题的。
     * 大文件处理可以使用readLines方法的另一个重载。
     * 下面的例子演示从一个大文件中逐行读取文本，并做行号计数。
     */
    @Test
    public void test3() throws IOException {
        String testFilePath = "e:\\svn.txt";
        File testFile = new File(testFilePath);
        CounterLine counter = new CounterLine();
        Files.asCharSource(testFile, Charsets.UTF_8).readLines(counter);
        logger.debug(counter.getResult());
    }

    static class CounterLine implements LineProcessor<Integer> {

        private int rowNum = 0;

        public boolean processLine(String line) throws IOException {
            logger.debug(line);
            rowNum++;
            return true;
        }

        public Integer getResult() {
            return rowNum;
        }
    }

    /**
     * 获取所有文本内容
     */
    @Test
    public void test4() throws IOException {
        File file = new File("e://svn.txt");
        String content = Files.asCharSource(file, Charsets.UTF_8).read();
        logger.debug(content);

        //		logger.debug(Files.readFirstLine(file, Charsets.UTF_8));
    }

    /**
     * 文件复制
     */
    @Test
    public void test5() {
        String sourceFileName = "e://svn.txt";
        String targetFileName = "e://svn_copy.txt";
        checkNotNull(sourceFileName, "Copy source file name must NOT be null.");
        checkNotNull(targetFileName, "Copy target file name must NOT be null.");
        final File sourceFile = new File(sourceFileName);
        final File targetFile = new File(targetFileName);
        try {
            Files.copy(sourceFile, targetFile);
        } catch (IOException fileIoEx) {
            logger.error(
                    "ERROR trying to copy file '" + sourceFileName + "' to file '" + targetFileName
                    + "' - " + fileIoEx.toString());
        }
    }

    /**
     * 比较文件内容是否相同
     */
    @Test
    public void test6() {
        String fileName1 = "e://svn.txt";
        String fileName2 = "e://svn_copy.txt";
        checkNotNull(fileName1, "First file name for comparison must NOT be null.");
        checkNotNull(fileName2, "Second file name for comparison must NOT be null.");
        final File file1 = new File(fileName1);
        final File file2 = new File(fileName2);
        try {
            logger.debug("File '" + fileName1 + "' " + (Files.equal(file1, file2) ? "IS" : "is NOT")
                         + " the same as file '" + fileName2 + "'.");
        } catch (IOException fileIoEx) {
            logger.error("ERROR trying to compare two files '" + fileName1 + "' and '" + fileName2
                         + "' - " + fileIoEx.toString());
        }
    }

    /*
     * Guava的Files类中还提供了其他一些文件的简捷方法。比如

     touch方法创建或者更新文件的时间戳。
     createTempDir()方法创建临时目录
     Files.createParentDirs(File) 创建父级目录
     //getChecksum(File)获得文件的checksum
     hash(File)获得文件的hash
     map系列方法获得文件的内存映射
     getFileExtension(String)获得文件的扩展名
     getNameWithoutExtension(String file)获得不带扩展名的文件名
     Guava的方法都提供了一些重载，这些重载可以扩展基本用法，我们也有必要去多了解一下，这些重载的方法。
     */
}
