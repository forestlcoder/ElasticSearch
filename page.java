//query build
//send page and pageCount params

while (true) {
			if (pageCount == 0) {// 下载专用
				tempjson = downLoad(query, tempjson, datas);
				return tempjson;
			} else if (pageList.isEmpty() || page - 2 < 0) {
				//首页
				response = es.getClient().prepareSearch(userIndexName).setSize(pageCount).addSort("_uid", SortOrder.ASC)
						.setQuery(query).get();
			} else {
				response = es.getClient().prepareSearch(userIndexName).setSize(pageCount).addSort("_uid", SortOrder.ASC)
						.searchAfter(pageList.get(page - 2))//
						.setQuery(query).get();
			}
			SearchHit[] searchHits = response.getHits().getHits();
			for (SearchHit sh : searchHits) {
				lastSearchHit = sh;
				Map<String, Object> resultMap = sh.getSource();
				String id = sh.getId();
				JSONObject json = new JSONObject();
				json = saveJsonData(json, id, resultMap);//构造数据
				datas.add(json);
			}

			if (!isRepeatFlag(lastSearchHit.getSortValues(), pageList)) {
				pageList.add(lastSearchHit.getSortValues());
			}


private boolean isRepeatFlag(Object[] tempList, List<Object[]> pageList) {
		for (Object[] pageObj: pageList){
			if(tempList[0].equals(pageObj[0]))
				return true;
		}
		return false;
	}
