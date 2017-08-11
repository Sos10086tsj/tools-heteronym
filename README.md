# tools-heteronym
基于jpinyin扩展了自定义多音字字库

## 配置说明
默认配置文件 pinyin.txt  每种类型通过 "[]" 分隔离，拼音影响单词用过"/"分隔。必须换行。
可以通过ZaPinyinHelper.convertToPinyinString接口传入自定义的配置文件。支持inputstream和classpath。

  [name]
  na:娜
  shan:单
  [word]
  enuo:婀娜
  
