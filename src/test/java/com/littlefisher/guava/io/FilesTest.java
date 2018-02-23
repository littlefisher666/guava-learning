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

public class FilesTest {

    private static Logger logger = LogManager.getLogger(FilesTest.class);

    /** 文件目录 */
    private static final String FILE_PATH = "/Users/littlefisher/Documents/";

    /**
     *
     * Files.write
     *
     * Guava的文件写入
     */
    @Test
    public void test1() throws IOException {
        String fileName = FILE_PATH + "test.txt";
        String contents = "hello world";
        checkNotNull(fileName, "Provided file name for writing must NOT be null.");
        checkNotNull(contents, "Unable to write null contents.");
        final File newFile = new File(fileName);
        Files.write(contents.getBytes(), newFile);
    }

    /**
     *
     * Files.readLines
     *
     * 获取文件内容
     */
    @Test
    public void test2() throws IOException {
        String testFilePath = FILE_PATH + "test.txt";
        File testFile = new File(testFilePath);
        List<String> lines = Files.readLines(testFile, Charsets.UTF_8);
        for (String line : lines) {
            logger.debug(line);
        }
    }

    /**
     *
     * Files.asCharSource
     *
     * 注意这里的readLines方法返回的是List<String>的接口，这对于大文件处理是会有问题的。
     * 大文件处理可以使用readLines方法的另一个重载。
     * 下面的例子演示从一个大文件中逐行读取文本，并做行号计数。
     */
    @Test
    public void test3() throws IOException {
        String testFilePath = FILE_PATH + "test.txt";
        File testFile = new File(testFilePath);
        CounterLine counter = new CounterLine();
        Files.asCharSource(testFile, Charsets.UTF_8)
                .readLines(counter);
        logger.debug(counter.getResult());
    }

    static class CounterLine implements LineProcessor<Integer> {

        private int rowNum = 0;

        @Override
        public boolean processLine(String line) {
            logger.debug(line);
            rowNum++;
            return true;
        }

        @Override
        public Integer getResult() {
            return rowNum;
        }
    }

    /**
     *
     * Files.asCharSource
     *
     * 获取所有文本内容
     */
    @Test
    public void test4() throws IOException {
        File file = new File(FILE_PATH + "test.txt");
        String content = Files.asCharSource(file, Charsets.UTF_8)
                .read();
        logger.debug(content);

        //		logger.debug(Files.readFirstLine(file, Charsets.UTF_8));
    }

    /**
     *
     * Files.copy
     *
     * 文件复制
     */
    @Test
    public void test5() throws IOException {
        String sourceFileName = FILE_PATH + "test.txt";
        String targetFileName = FILE_PATH + "test_copy.txt";
        checkNotNull(sourceFileName, "Copy source file name must NOT be null.");
        checkNotNull(targetFileName, "Copy target file name must NOT be null.");
        final File sourceFile = new File(sourceFileName);
        final File targetFile = new File(targetFileName);
        Files.copy(sourceFile, targetFile);
    }

    /**
     *
     * Files.equal
     *
     * 比较文件内容是否相同
     */
    @Test
    public void test6() throws IOException {
        String fileName1 = FILE_PATH + "test.txt";
        String fileName2 = FILE_PATH + "test_copy.txt";
        checkNotNull(fileName1, "First file name for comparison must NOT be null.");
        checkNotNull(fileName2, "Second file name for comparison must NOT be null.");
        final File file1 = new File(fileName1);
        final File file2 = new File(fileName2);
        logger.debug("File '" + fileName1 + "' " + (Files.equal(file1, file2) ? "IS" : "is NOT") +
                " the same as file '" + fileName2 + "'.");
    }

    /**
     * Files.createParentDirs(File)
     *
     * 创建父级目录，会递归创建
     */
    @Test
    public void test7() throws IOException {
        String fileName = FILE_PATH + "/aaa/bbb/ccc/test.txt";
        Files.createParentDirs(new File(fileName));
    }

    /**
     *
     * Files.getFileExtension
     *
     * 获取文件扩展名
     */
    @Test
    public void test8() {
        String fileName = FILE_PATH + "test.txt";

        String extensionName = Files.getFileExtension(fileName);
        logger.debug("extensionName: {}", extensionName);
    }

    /**
     * 获取不带扩展名的文件名
     */
    @Test
    public void test9() {
        String fileFullPathName = FILE_PATH + "test.txt";
        String fileNameWithoutExtension = Files.getNameWithoutExtension(fileFullPathName);

        logger.debug("fileNameWithoutExtension: {}", fileNameWithoutExtension);
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
