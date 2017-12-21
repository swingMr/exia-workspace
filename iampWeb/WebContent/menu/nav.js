var navs = [{
	"title": "基础管理",
	"icon": "&#xe600;",
	"spread": true,
	"children": [{
		"title":"用户管理",
		"icon": "&#xe613;",
		"href": "/iamp/menu/address?targetMenu=/platform/console/baseBiz/um/orgTree.do?type=user&all=true",
		"name":'yhgl'
	}, {
		"title": "APPID管理",
		"icon": "&#xe63c;",
		"href": "/iamp/app/list.do",
		"name":"appidgl"
	}, {
		"title": "会员账户管理",
		"icon": "&#xe613;",
		"href": "/iamp/member/list.do",
		"name":"hyzhgl"
	}, {
		"title": "定时任务管理",
		"icon": "&#xe637;",
		"href": "/iamp/task/list.do",
		"name":"dsrwgl"
	}, {
		"title": "系统参数配置",
		"icon": "&#xe62a;",
		"href": "/iamp/config/list.do",
		"name":"pzwjgl"
	}, {
		"title": "会员行为日志",
		"icon": "&#xe613;",
		"href": "/iamp/userOpLog/queryList.do",
		"name":"yhxwrz"
	}]
}, {
	"title": "对外服务配置",
	"icon": "&#xe614;",
	"spread": false,
	"children": [ {
		"title": "服务定义",
		"icon": "&#xe630;",
		"href": "/iamp/service/list.do",
		"name":"fwdy"
	}, {
		"title": "本体识别配置",
		"icon": "&#xe62a;",
		"href": "/iamp/ontologyRule/pageList",
		"name":"btsbpz"
	}, {
		"title": "文件格式转换配置",
		"icon": "&#x1002;",
		"href": "/iamp/fileconfig/find.do",
		"name":"wjgszhpz"
	}, {
		"title": "信息抽取规则配置",
		"icon": "&#x1002;",
		"href": "/iamp/extractRule/list",
		"name":"xxcqgzpz"
	}]
},{
	"title": "知识分析挖掘管理",
	"icon": "&#xe615;",
	"spread": false,
	"children": [{
		"title": "文本分词",
		"icon": "&#xe639;",
		"href": "/iamp/main/datadig/segmentation.jsp",
		"name":'wbfc'
	},{
		"title": "文本分类",
		"icon": "&#xe630;",
		"href": "/iamp/main/datadig/type.jsp",
		"name":'wbfl'
	},{
		"title": "关键词和摘要提取",
		"icon": "&#xe63c;",
		"href": "/iamp/main/datadig/keywordAndSummary.jsp",
		"name":'gjchzytq'
	}, {
		"title": "文本聚类",
		"icon": "&#xe631;",
		"href": "tab.html",
		"name":'wbjl'
	}, {
		"title": "实体识别",
		"icon": "&#xe631;",
		"href": "tab.html",
		"name":'wbjl'
	}, {
		"title": "分类器生成",
		"icon": "&#xe631;",
		"href": "tab.html",
		"name":'wbjl'
	},{
		"title": "Python程序管理",
		"icon": "&#x1002;",
		"href": "/iamp/python/list.do",
		"name":"pythoncxgl"
	}]
},{
	"title": "领域知识本体管理",
	"icon": "&#xe628;", 
	"name":'lyzsbtgl',
	"spread": false,
	"children": [{
		"title": "领域本体维护",
		"icon": "&#xe64f;",
		"name":'lybtwh',
		"href":"/iamp/menu?targetMenu=/ExKEGov/domain/home.jsp"
	},{
		"title": "通用本体维护",
		"icon": "&#xe639;",
		"name":'tybtwh',
		"href":"/iamp/menu?targetMenu=/ExKEOntology/guard/concept/view.jsp"
	},{
		"title": "概念关系定义",
		"icon": "&#xe62e;",
		"name":'gngxdy',
		"href":"/iamp/menu?targetMenu=/ExKEOntology/guard/concept/relationDefinitionView.jsp"
	},{
		"title": "关系批量维护",
		"icon": "&#xe60f;",
		"href": "/iamp/menu?targetMenu=/ExKEOntology/guard/relation/relationManage.jsp",
		"name":'gxplwh'
	},{
		"title": "在线解构工具",
		"icon": "&#xe62d;",
		"href": "/iamp/menu?targetMenu=/ExKEGov/tools/onlineBuilde/importFile.jsp",
		"name":'zxjggj'
	},{
		"title": "知识本体检索",
		"icon": "&#xe64c;",
		"href": "/iamp/menu?targetMenu=/ExKEOntology/guard/search/searchEngine.jsp",
		"name":'zsbtjs'
	}]
},  {
	"title": "综合知识库管理",
	"icon": "&#xe631;",
	"href": "https://www.baidu.com", 
	"name":'zhzskgl',
	"spread": false,
	"children": [{
		"title": "文本语料管理",
		"icon": "&#xe60a;",
		"href": "/iamp/textcorpus/list.do",
		"name":'wbylgl'
	},{
		"title": "信息资源批量加工",
		"icon": "&#xe621;",
		"href": "/iamp/resworkitem/list.do",
		"name":"xxzypljg"
	},{
		"title": "信息资源库定义&迁移",
		"icon": "&#xe64d;",
		"href": "/iamp/resourceLib/list.do",
		"name":'xxzykdyqy'
	},{
		"title": "信息资源维护",
		"icon": "&#xe64c;",
		"href": "/iamp/resourceLib/getAll.do",
		"name":'xxzywh'
	},{
		"title": "资源入库监控",
		"icon": "&#xe6b2;",
		"href": "/iamp/monitoringInfo/list",
		"name":'zyrkjk'
	}
	]
}, {
	"title": "机器人问答管理",
	"icon": "&#xe6af;",
	"href": "https://www.baidu.com", 
	"name":'jqrwdgl',
	"spread": false,
	"children": [{
		"title": "机器人定义",
		"icon": "&#xe60a;",
		"href": "/iamp/robotDef/list.do",
		"name":'jqrdy'
	},{
		"title": "答案部件配置规则",
		"icon": "&#xe621;",
		"href": "/iamp/answerPart/list.do",
		"name":"dabjpzgz"
	},{
		"title": "问答对话监控",
		"icon": "&#xe64d;",
		"href": "construct.jsp",
		"name":'wddhjk'
	}
	]
},{
	"title": "全文数据库管理",
	"icon": "&#xe62c;",
	"href": "",
	"spread": false,
	"href": "https://www.github.com/",
	"name":'qwsjkgl'
}];