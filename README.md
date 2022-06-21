# build-jar-template
实现以下内容：

从外部加载配置文件。(resources目录下存放 FILE_NAME即可)
日志配置。（使用外部日志框架）
javadoc标准化文档。
使用maven源码插件。
私服相关配置：拉取仓库和部署仓库。

使用hutool的LogFactory.get 自动识别引入的日志框架，从而创建对应日志框架的门面Log对象。参考hutool官网
