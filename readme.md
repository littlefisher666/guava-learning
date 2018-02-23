#### `Guava Learning`
该工程主要用以学习`Guava`工具包的常用功能

#### 前提
该工程是以`jdk1.8`为版本的代码，所以`lambda`表达式会大量的出现。如果自己的工程是`jdk1.8`以下的，请自行把`lambda`表达式恢复为原有样式。

#### 详细介绍
下面列举自我感觉比较常用的功能

##### `CaseFormat` 字符串大小写改造
`com.littlefisher.guava.base.CaseFormatTest`
具体使用请参考模块代码

##### `Objects`
`com.littlefisher.guava.base.ObjectsTest`

###### `Objects.equal` 比较两个对象是否相等
在`jdk1.7`及以后的版本，可以使用`java.util.Objects.equals`方法进行代替
但是在做对象判空时，不太建议使用该方法

##### `Splitter` 字符串拆分
`com.littlefisher.guava.base.SplitterTest`
比较于`jdk`提供的`split`方法而言，该类分隔的更彻底一些。使用`jdk`提供的`split`方法，有时会莫名其妙的少些字符串。并且该`Splitter`类可以直接拆分成`List`集合，并做去重操作，而`split`方法返回的是数组。

##### `Strings`
`com.littlefisher.guava.base.StringsTest`

###### `Strings.commonPrefix`
字符串补前缀

###### `Strings.commonSuffix`
字符串补后缀

###### `Strings.padStart`
字符串前补齐某个字符
例如`10`，需要补齐成`6`个字符长度的`000010`

###### `Strings.padEnd`
字符串后补齐某个字符，跟上面的类似

##### `BiMap` 双向`Map`
`com.littlefisher.guava.collect.BiMapTest`
一般的`Map`都是根据`key`查找到`value`，这个`Map`可以根据`value`查找到`key`
业务侧已有是用到，`key`和`value`分别是`userId`和`resourceId`

##### 集合创建
`Maps.newHashMap()`
`Lists.newArrayList()`
`Sets.newHashSet()`
`Lists.newLinkedList()`
`Maps.newHashMap()`
这些都是业务代码里使用`Guava`最多的代码了

##### `Collections2` 集合处理类
`com.littlefisher.guava.collect.CollectionTest`

###### `Collections2.filter` 对集合进行过滤

##### `Iterators` 集合迭代器工具类
`com.littlefisher.guava.collect.IteratorsTest`

###### `Iterators.all`
返回一个`boolean`值，校验集合是否都满足一个条件

###### `Iterators.any`
返回一个`boolean`值，校验集合是否存在一个元素满足条件的
举个例子，查询`productDetail`后，校验返回的合同列表中是否有授信合同模板

###### `Iterators.get`
返回下标位置的元素。如果仅这一个功能的话，跟集合直接调用`get`方法没什么区别，最主要的是该方法重载了一个方法，可以传入一个默认值，如果下标越界，会返回默认值

###### `Iterators.filter`
对集合进行元素过滤，返回一个过滤后的集合`Iterator`。`Iterator`可以通过`Lists.newArrayList()`转为集合。

###### `Iterators.find`
在集合中查找满足条件的元素。该方法也有重载方法，可以设置默认值，否则会抛异常。

###### `Iterators.transform`
根据当前构造一个子集合，例如原集合是`List<CreditCreditApply>`，而需要构造出来`List<String>`，其中都是`creditApplyId`。即可使用该方法。

###### `Iterators.removeIf`
根据条件删去集合中的元素。`for`循环中是不能直接删除元素的，否则会抛异常。但是可以使用该方法进行替换，其底层实现就是使用迭代器处理的。

##### `Multimap` 一个`key`可以对应一个`List`的`Map`
`com.littlefisher.guava.collect.MultiMapTest`
正常情况下，`Map`中`key`都是唯一的。而使用`Multimap`，当做`put`操作时，如果`key`已存在，则`value`会成为一个集合。

##### `Files` 文件处理工具类
`com.littlefisher.guava.io.FilesTest`

###### `Files.write`
文件写入。入参为`byte`数组和写入文件`File`对象

###### `Files.readLines`
按行读取文件，返回值为`List<String>`

###### `Files.asCharSource`
该方法具体参考代码，有可以记录行号的，也有可以一次性读取所有内容的。

###### `Files.copy`
文件复制

###### `Files.equal`
校验两个文件的文本内容是否相同

###### `Files.createParentDirs`
递归创建父目录

###### `Files.getFileExtension`
获取文件扩展名

###### `Files.getNameWithoutExtension`
获取不带扩展名的文件名