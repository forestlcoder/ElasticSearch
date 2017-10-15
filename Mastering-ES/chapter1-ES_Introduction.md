##ElasticSearch 简介
### Lucene基本概念
1、document:文档。类似关系型数据库中的表，包含一个或多个字段，存放一条数据记录。
2、field:字段。文档中的一个片段，类似数据库中数据项。包含了字段的名称和内。
3、term:词项。搜索时的一个单位,代表文本中的某个值。
4、token:词条。词项在字段中的一次出现，包括词项的文本、开始和结束的位移以及类型。

###倒排索引(inverted index)；
倒排索引是面向词项的，而不是文档。每个此项直系那个该词项所出现过的文档数，加快搜索的速度。

###ElasticSearch基本概念
		ES 						  关系型数据库
	索引			<-->					数据库
	文档			<-->					数据表
	映射			<-->					生成或查看表结构
	类型			<-->					同一个数据库中不同表

	ES DSL(Domain Specific Language):
	GET TABLE/_search
{
  "query": {
    "term": {
      "FIELD": {
        "value": "xx"
      }
    }
  }
}
和以下SQL语句类似:
	SELECT *
	FROM TABLE
	WHERE FILED = 'xx'
