var INTERFACE_TYPES = [ {
	'description' : '基础服务接口',
	'type' : 'base',
	'children' : [ {
		'description' : 'APP基础服务',
		'type' : 'base.security'
	},{
		'description' : '会员账户服务',
		'type' : 'base.member'
	},{
		'description' : '定时任务服务',
		'type' : 'base.task'
	} ]
}, {
	'description' : '语义识别&检索服务',
	'type' : 'ontology',
	'children' : [ {
		'description' : '识别知识概念',
		'type' : 'ontology.recognise'
	}, {
		'description' : '检索知识本体',
		'type' : 'ontology.search'
	}, {
		'description' : '获取本体引文',
		'type' : 'ontology.quotation'
	}, {
		'description' : '检索信息资源',
		'type' : 'ontology.textsearch'
	}, {
		'description' : '机器人问答服务',
		'type' : 'ontology.chat'
	}]
}, {
	'description' : '知识挖掘&分析服务',
	'type' : 'calculate',
	'children' : [ {
		'description' : '服务查询',
		'type' : 'calculate.search'
	}, {
		'description' : '调试扩展服务',
		'type' : 'calculate.debug'
	}, {
		'description' : '内置服务',
		'type' : 'calculate.services'
	} ]
}, 
{
	'description' : '信息资源获取',
	'type' : 'resourceGet',
	'children' : [ {
		'description' : '信息资源库',
		'type' : 'resourceLib'
	}, {
		'description' : '信息资源',
		'type' : 'resource'
	}]
},
/*{
	'description' : '信息资源',
	'type' : 'resource',
	'children' : [ {
		'description' : '获取指定信息资源的属性信息',
		'type' : 'resource.one'
	}, {
		'description' : '获取信息资源的正文内容',
		'type' : 'resource.content'
	},{
		'description' : '获取信息资源指定附件的文件内容',
		'type' : 'resource.attach'
	},{
		'description' : '获取信息资源的数量',
		'type' : 'resource.count'
	},{
		'description' : '通过参数获取资源列表',
		'type' : 'resource.resourcelist'
	},{
		'description' : '通过资源url获取资源列表',
		'type' : 'resource.resourceListByUrl'
	},{
		'description' : '新建资源',
		'type' : 'resource.newResource'
	},{
		'description' : '更新资源',
		'type' : 'resource.updateResource'
	},{
		'description' : '通过资源url获取资源的详细内容',
		'type' : 'resource.resourceListByUrlAndAttributes'
	}]
},*/
{
	'description' : '文件格式转换服务',
	'type' : 'file',
	'children' : [ {
		'description' : '上传下载文件',
		'type' : 'file.uploadAndDownload'
	}, {
		'description' : '文件转成TXT',
		'type' : 'file.convertTxt'
	}, {
		'description' : '文件转成PDF',
		'type' : 'file.convertPdf'
	}, {
		'description' : '文件转成HTML',
		'type' : 'file.convertHtml'
	}, {
		'description' : '文件转成图片',
		'type' : 'file.convertPic'
	}, {
		'description' : '文件转成WORD',
		'type' : 'file.convertWord'
	} ]
} ];

var INTERFACES = {
	'base.security' : [ {
		'description' : '获取服务访问Token',
		'url' : '/services/base/security/gettoken',
		'arguments' : [ {
			'name' : 'appCode',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'appPwd',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'timestamp',
			'notNull' : true,
			'type' : 'int'
		} ]
	},{
		'description' : '清除memcached缓存',
		'url' : '/services/base/security/clearCache',
		'arguments' : [ {
			'name' : 'group',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '授权某个会员使用APP',
		'url' : '/services/base/security/grant/member',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'memberName',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'memberId',
			'notNull' : false,
			'type' : 'String'
		} ]
	}],
	'base.member' : [ {
		'description' : '获取app关注会员清单',
		'url' : '/services/base/member/appmember/list',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'appGroup',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'page',
			'notNull' : true,
			'type' : 'int'
		}, {
			'name' : 'pageSize',
			'notNull' : true,
			'type' : 'int'
		} ]
	},{
		'description' : '注册会员信息并进行关注当前应用',
		'url' : '/services/base/member/register',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'memberAccount',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'memberName',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'qqNum',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'emailAddress',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'wechatNum',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'phoneNumber',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'remark',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'memberPsw',
			'notNull' : false,
			'type' : 'String'
		},
		{
			'name' : 'group',
			'notNull' : false,
			'type' : 'String'
		},
		{
			'name' : 'domains',
			'notNull' : false,
			'type' : 'String'
		},
		{
			'name' : 'extendAttribute',
			'notNull' : false,
			'type' : 'String'
		}]
	},{
		'description' : '获取指定的会员信息',
		'url' : '/services/base/member/appmember',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'idType',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'loginId',
			'notNull' : true,
			'type' : 'String'
		} ]
	},{
		'description' : '验证会员密码',
		'url' : '/services/base/member/login',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'memberAccount',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'memberPsw',
			'notNull' : true,
			'type' : 'String'
		} ]
	},{
		'description' : '修改会员信息',
		'url' : '/services/base/member/update',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'memberAccount',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'qqNum',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'emailAddress',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'wechatNum',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'phoneNumber',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'remark',
			'notNull' : false,
			'type' : 'String'
		},
		{
			'name' : 'domains',
			'notNull' : false,
			'type' : 'String'
		},
		{
			'name' : 'extendAttribute',
			'notNull' : false,
			'type' : 'String'
		}]
	},{
		'description' : '修改会员密码',
		'url' : '/services/base/member/modifyMemberPsw',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'memberAccount',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'memberPsw',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'newMemberPsw',
			'notNull' : true,
			'type' : 'String'
		} ]
	} ],
	'base.task' : [ {
		'description' : '获取当前已经注册的定时任务清单',
		'url' : '/services/base/task/list',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'taskType',
			'notNull' : false,
			'type' : 'int'
		}, {
			'name' : 'taskName',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'page',
			'notNull' : false,
			'type' : 'int'
		}, {
			'name' : 'pageSize',
			'notNull' : false,
			'type' : 'int'
		} ]
	},{
		'description' : '管理控制定时任务的运行状态',
		'url' : '/services/base/task/ctrl',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'taskId',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'status',
			'notNull' : true,
			'type' : 'int'
		}]
	}],
	'ontology.recognise' : [ {
		'description' : '根据文本输入获取五要素',
		'url' : '/services/ontology/recognise/concepts',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'context',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'title',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'keyWord',
			'notNull' : false,
			'type' : 'String[]'
		}, {
			'name' : 'text',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'clsNames',
			'notNull' : false,
			'type' : 'String[]'
		} ]
	},
	{
		'description' : '生成HTML及纯文本内容的标引',
		'url' : '/services/ontology/recognise/docIndex',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'context',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'text',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'words',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'wordClazz',
			'notNull' : false,
			'type' : 'String'
		},
		{
			'name' : 'sectionClazz',
			'notNull' : false,
			'type' : 'String'
		} ,
		{
			'name' : 'sections',
			'notNull' : false,
			'type' : 'String'
		} ]
	},
	{
		'description' : '从文件中提取信息：从DOC、PDF、HTML、TXT文件提取结构化数据',
		'url' : '/services/ontology/recognise/fileExtract',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'context',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'file',
			'notNull' : true,
			'type' : 'file'
		}, {
			'name' : 'genre',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'fileType',
			'notNull' : true,
			'type' : 'String'
		}]
	},
	{
		'description' : '从纯文本中提取信息：从纯文本内容中提取结构化数据',
		'url' : '/services/ontology/recognise/textExtract',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'context',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'text',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'genre',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'fileType',
			'notNull' : true,
			'type' : 'String'
		}]
	}],
	'ontology.search' : [ {
		'description' : '获取大领域或子领域清单',
		'url' : '/services/ontology/search/domains',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'domainName',
			'notNull' : true,
			'type' : 'String'
		} ]
	},{
		'description' : '获取指定领域下的所有关键词',
		'url' : '/services/ontology/search/keywords',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'domainName',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'limit',
			'notNull' : true,
			'type' : 'int'
		} ]
	}, {
		'description' : '获取指定领域下的概念清单',
		'url' : '/services/ontology/search/concepts',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'domainName',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'parentNames',
			'notNull' : true,
			'type' : 'String[]'
		}, {
			'name' : 'type',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'limit',
			'notNull' : false,
			'type' : 'int'
		} ]
	}, {
		'description' : '根据概念名称获取概念对象',
		'url' : '/services/ontology/search/conceptsByNames',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'conceptNames',
			'notNull' : true,
			'type' : 'String[]'
		} ]
	},{
		'description' : '获取指定概念的相关概念',
		'url' : '/services/ontology/search/relevance',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'srcIds',
			'notNull' : true,
			'type' : 'String[]'
		}, {
			'name' : 'relationDefIds',
			'notNull' : false,
			'type' : 'String[]'
		}, {
			'name' : 'direction',
			'notNull' : false,
			'type' : 'int'
		}, {
			'name' : 'objParentNames',
			'notNull' : false,
			'type' : 'String[]'
		}, {
			'name' : 'objType',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'objLimit',
			'notNull' : false,
			'type' : 'int'
		} ]
	} ,{
		'description' : '获取概念',
		'url' : '/services/ontology/search/getConcept',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'id',
			'notNull' : true,
			'type' : 'String'
		} ]
	},{
		'description' : '通过主题词获取本体集合',
		'url' : '/services/ontology/search/getOntologiesByKeywords',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'words',
			'notNull' : true,
			'type' : 'String'
		} ]
	},{
		'description' : '通过主题词获取概念集合',
		'url' : '/services/ontology/search/getConceptsByKeywords',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'words',
			'notNull' : true,
			'type' : 'String'
		} ]
	},{
		'description' : '获取相关本体',
		'url' : '/services/ontology/search/getOntologiesByIds',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'ids',
			'notNull' : true,
			'type' : 'String'
		} ]
	},{
		'description' : '获取相关本体',
		'url' : '/services/ontology/search/getOntologies',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'conceptId',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'conditions',
			'notNull' : true,
			'type' : 'String'
		} ]
	},{
		'description' : '获取分词',
		'url' : '/services/ontology/search/getOntologies',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'text',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '通过主体和客体获取行为',
		'url' : '/services/ontology/search/getActionConceptDefsByBodyAndObject',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'context',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'bodyConceptId',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'objConceptId',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '获取相关知识',
		'url' : '/services/ontology/search/getRelatedKnowledgesByAction',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'context',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'actConceptId',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '通过关键词获取本体和资源',
		'url' : '/services/ontology/search/getOntologiesAndInfosByKeywords',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'context',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'words',
			'notNull' : true,
			'type' : 'String'
		}]
	}, {
		'description' : '根据概念名称获取概念对象',
		'url' : '/services/ontology/search/conceptsByNames',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'conceptNames',
			'notNull' : true,
			'type' : 'String[]'
		} ]
	}, {
		'description' : '获取主题词流数据',
		'url' : '/services/ontology/search/getThemeWordsOutputStream',
		'arguments' : []
	} ,{
		'description' : '分词',
		'url' : '/services/ontology/search/getTermsByText',
		'arguments' : [ {
			'name' : 'text',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '获取外延概念',
		'url' : '/services/ontology/search/getExtensionalConcepts',
		'arguments' : [ {
			'name' : 'conceptId',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'type',
			'notNull' : true,
			'type' : 'String'
		} , {
			'name' : 'num',
			'notNull' : true,
			'type' : 'int'
		} ]
	},{
		'description' : '获取内涵概念',
		'url' : '/services/ontology/search/getIntensionalConcepts',
		'arguments' : [ {
			'name' : 'conceptId',
			'notNull' : true,
			'type' : 'String'
		}]
	} ,{
		'description' : '获取主体的客体',
		'url' : '/services/ontology/search/getBodysOfObjects',
		'arguments' : [ {
			'name' : 'objectIds',
			'notNull' : true,
			'type' : 'String'
		}]
	} ,{
		'description' : '根据概念id集合和关系名称及关系查询本体',
		'url' : '/services/ontology/search/getOntologiesByConceptIdsAndConditions',
		'arguments' : [ {
			'name' : 'conceptIds',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'conditions',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '通过名字获取关系定义，此方法查缓存',
		'url' : '/services/ontology/search/getRelationDefinitionsByNames',
		'arguments' : [ {
			'name' : 'names',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '获取某个检索词的推荐检索词汇',
		'url' : '/services/ontology/search/getRecommendedKeywords',
		'arguments' : [ {
			'name' : 'words',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '根据关键词句检索相关知识',
		'url' : '/services/ontology/search/searchConceptDefsOfText',
		'arguments' : [ {
			'name' : 'text',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '获取五要素',
		'url' : '/services/ontology/search/getRelatedFiveElements',
		'arguments' : [ {
			'name' : 'keywords',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '根据传入的信息查找相关性内容',
		'url' : '/services/ontology/search/searchRelevantContent',
		'arguments' : [ {
			'name' : 'text',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'domainIds',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'parentIds',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : 'searchSBO',
		'url' : '/services/ontology/search/searchSBO',
		'arguments' : [ {
			'name' : 'text',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : 'ruleCATClassifyText',
		'url' : '/services/ontology/search/ruleCATClassifyText',
		'arguments' : [ {
			'name' : 'text',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : 'nagaoText',
		'url' : '/services/ontology/search/nagaoText',
		'arguments' : [ {
			'name' : 'text',
			'notNull' : true,
			'type' : 'String'
		}]
	}],
	'ontology.textsearch' : [ {
		'description' : '根据五要素检索信息资源',
		'url' : '/services/ontology/textsearch/byontology',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'context',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'ontoOfReqJson',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'infoSources',
			'notNull' : true,
			'type' : 'String[]'
		}, {
			'name' : 'page',
			'notNull' : false,
			'type' : 'int'
		}, {
			'name' : 'pageSize',
			'notNull' : false,
			'type' : 'int'
		} ]
	}, {
		'description' : '全文检索SQL语句检索资源',
		'url' : '/services/ontology/textsearch/sql',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'sql',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'infoSources',
			'notNull' : false,
			'type' : 'String[]'
		}, {
			'name' : 'page',
			'notNull' : false,
			'type' : 'int'
		}, {
			'name' : 'pageSize',
			'notNull' : false,
			'type' : 'int'
		} ]
	} , {
		'description' : '根据条件查询信息资源',
		'url' : '/services/ontology/textsearch/bycondition',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'context',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'resOfReqJson',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'infoSources',
			'notNull' : false,
			'type' : 'String[]'
		}, {
			'name' : 'orderName',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'orderType',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'page',
			'notNull' : false,
			'type' : 'int'
		}, {
			'name' : 'pageSize',
			'notNull' : false,
			'type' : 'int'
		} ]
	} , {
		'description' : '全文检索',
		'url' : '/services/ontology/textsearch/searchText',
		'arguments' : [ {
			'name' : 'conditions',
			'notNull' : true,
			'type' : 'String'
		}]
	}, {
		'description' : 'getComplexUrl',
		'url' : '/services/ontology/textsearch/getComplexUrl',
		'arguments' : [ {
			'name' : 'simpleUrl',
			'notNull' : true,
			'type' : 'String'
		}]
	}, {
		'description' : 'getSimpleUrl',
		'url' : '/services/ontology/textsearch/getSimpleUrl',
		'arguments' : [ {
			'name' : 'complexUrl',
			'notNull' : true,
			'type' : 'String'
		}]
	}, {
		'description' : '提取文本摘要和主题词',
		'url' : '/services/ontology/textsearch/absText',
		'arguments' : [ {
			'name' : 'text',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'numOfSub',
			'notNull' : true,
			'type' : 'int'
		},{
			'name' : 'percent',
			'notNull' : true,
			'type' : 'int'
		},{
			'name' : 'dictName',
			'notNull' : true,
			'type' : 'String'
		}]
	}],'ontology.chat' : [ {
		'description' : '当前会员与机器人建立对话关系',
		'url' : '/services/chat/createsession',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'context',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'robotId',
			'notNull' : false,
			'type' : 'String'
		}]
	}, {
		'description' : '关闭指定聊天或会员自己的聊天对话',
		'url' : '/services/chat/closesession',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'context',
			'notNull' : false,
			'type' : 'String'
		}, {
			'name' : 'sessionId',
			'notNull' : true,
			'type' : 'String'
		}]
	} , {
		'description' : '查询当前会员与机器人之间的正在进行中的对话信息',
		'url' : '/services/chat/querysession',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'context',
			'notNull' : true,
			'type' : 'String'
		}]
	} , {
		'description' : '查询指定对话的会员与机器人之间的聊天记录，按发言时间从大到小排序',
		'url' : '/services/chat/querymsg',
		'arguments' : [{
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'context',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'sessionId',
			'notNull' : true,
			'type' : 'String'
		}, {
			'name' : 'page',
			'notNull' : false,
			'type' : 'int'
		}, {
			'name' : 'pageSize',
			'notNull' : false,
			'type' : 'int'
		}]
	}, {
		'description' : '会员给机器人发送消息，机器人对消息内容进行回复',
		'url' : '/services/chat/ask',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'context',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'sessionId',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'msgType',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'originalContent',
			'notNull' : true,
			'type' : 'String'
		}]
	}, {
		'description' : '测试url',
		'url' : '/services/ontology/getinfo/getInformationsByUrlAndAttribute',
		'arguments' : [ {
			'name' : 'attribute',
			'notNull' : true,
			'type' : 'String'
		},{
			'name' : 'url',
			'notNull' : true,
			'type' : 'String'
		}]
	}],
	'calculate.search' : [ {
		'description' : '获取所有服务列表',
		'url' : '/services/calculate/servicelist',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		} ]
	}, {
		'description' : '获取服务基本信息',
		'url' : '/services/calculate/info/{服务名称}',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		} ]
	} ],
	'calculate.debug' : [ {
		'description' : '调用扩展服务',
		'url' : '/services/call/{服务名称}',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		} ]
	} ],
	'calculate.services' : [ {
		'description' : '中文文本分词',
		'url' : '/services/calculate/participle',
		'arguments' : [ 
		    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'text', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'tagPart', 'notNull' : false, 'type' : 'Boolean'},
		    { 'name' : 'patten', 'notNull' : false, 'type' : 'int'}
		]
    }, {
    	'description' : '关键词和摘要提取',
		'url' : '/services/calculate/abstract',
		'arguments' : [ 
		    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'title', 'notNull' : false, 'type' : 'String'},
		    { 'name' : 'text', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'keyWordLimit', 'notNull' : false, 'type' : 'int'},
		    { 'name' : 'abstractLength', 'notNull' : false, 'type' : 'int'}
		]
    },{
    	'description' : '生成文本数据的标签',
		'url' : '/services/calculate/gentag',
		'arguments' : [ 
		    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'title', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'text', 'notNull' : false, 'type' : 'String'},
		    { 'name' : 'recDomain', 'notNull' : false, 'type' : 'boolean'},
		    { 'name' : 'recHierarchy', 'notNull' : false, 'type' : 'boolean'},
		    { 'name' : 'sourceUnit', 'notNull' : false, 'type' : 'String'},
		    { 'name' : 'publishDate', 'notNull' : false, 'type' : 'String'}
		]
    },{
    	'description' : '根据需求主题获取关键段落',
		'url' : '/services/calculate/keypara',
		'arguments' : [ 
		    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'reqSubject', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'text', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'abstractLength', 'notNull' : false, 'type' : 'Int'}
		]
    }, {
    	'description' : '文本分类',
		'url' : '/services/calculate/classify',
		'arguments' : [ 
		    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'text', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'modelName', 'notNull' : true, 'type' : 'String'}
		]
    }],
    'file.uploadAndDownload' : [{
			'description' : '上传一个临时文件',
			'url' : '/services/file/ud/upload',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'file', 'notNull' : true, 'type' : 'file'},
			    { 'name' : 'fileTitle', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'fileExt', 'notNull' : true, 'type' : 'String'}
			]
	},{
		'description' : '下载一个临时文件',
		'url' : '/services/file/ud/download/{临时文件的唯一标识}',
		'arguments' : []
	}],
	'file.convertPic':[
		{
			'description' : '上传一个文件并转成图片文件',
			'url' : '/services/file/convert/pic/upload',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'file', 'notNull' : true, 'type' : 'file'},
			    { 'name' : 'fileTitle', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'fileExt', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'startPage', 'notNull' : false, 'type' : 'int'},
			    { 'name' : 'pageSize', 'notNull' : false, 'type' : 'int'}
			]
	},{
		'description' : '把指定临时文件转成图片文件',
		'url' : '/services/file/convert/pic/upload/{临时文件的唯一标识}',
		'arguments' : []
	}
	],
	'resourceLib':[
		{
			'description' : '获取正常状态的资源库清单',
			'url' : '/services/resdb/list',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
			]
		},{
			'description' : '查看资源库详情',
			'url' : '/services/resdb/dbinfo/{资源库编号}',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			]
		}
	],
	'resource':[
		{
			'description' : '获取指定信息资源的属性信息',
			'url' : '/services/res/{库编号}/{资源id}/info',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
			]
		},{
			'description' : '获取信息资源的正文内容',
			'url' : '/services/res/{库编号}/{资源id}/text',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
			]
		},{
			'description' : '获取信息资源指定附件的文件内容',
			'url' : '/services/res/{库编号}/{资源id}/attach/{文件Id}',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
			]
		},{
			'description' : '获取信息资源的数量',
			'url' : '/services/res/count',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'libNum', 'notNull' : true, 'type' : 'String'}
			]
		}, {
	      	  'description' : '通过资源url获取资源列表',
	    	  'url' : '/services/res/resourceListByUrl',
	    	  'arguments' : [ 
	                 { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
	                 { 'name' : 'url', 'notNull' : true, 'type' : 'String'},
	                 { 'name' : 'pageNo', 'notNull' : true, 'type' : 'int'},
	                 { 'name' : 'pageSize', 'notNull' : true, 'type' : 'int'}
	             ]
			},{
	        	'description' : '通过资源url获取资源的详细内容',
	        	'url' : '/services/res/text',
	        	'arguments' : [ 
		               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
		               { 'name' : 'url', 'notNull' : true, 'type' : 'String'},
		               { 'name' : 'attributes', 'notNull' : true, 'type' : 'String'}
	               ]
	        },{
			'description' : '通过参数获取资源列表',
			'url' : '/services/res/resourceList',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'libNum', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'catalogNum', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'searchTitle', 'notNull' : false, 'type' : 'String'},
			    { 'name' : 'pageNo', 'notNull' : true, 'type' : 'int'},
			    { 'name' : 'pageSize', 'notNull' : true, 'type' : 'int'}
			]
		}, {
     	   'description' : '新建资源',
    	   'url' : '/services/res/createResource',
    	   'arguments' : [ 
                  { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
                  { 'name' : 'channelId', 'notNull' : true, 'type' : 'String'},
                  { 'name' : 'channelName', 'notNull' : true, 'type' : 'String'},
                  { 'name' : 'libName', 'notNull' : true, 'type' : 'String'},
                  { 'name' : 'title', 'notNull' : true, 'type' : 'String'},
                  { 'name' : 'content', 'notNull' : true, 'type' : 'String'}
              ]
		}, {
	       	 'description' : '更新资源',
	    	 'url' : '/services/res/updateResource',
	    	 'arguments' : [ 
	                { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
	                { 'name' : 'channelName', 'notNull' : true, 'type' : 'String'},
	                { 'name' : 'libName', 'notNull' : true, 'type' : 'String'},
	                { 'name' : 'title', 'notNull' : true, 'type' : 'String'},
	                { 'name' : 'attributes', 'notNull' : true, 'type' : 'String'}
	    	    ]
		},{
        	'description' : '获取资源目录',
        	'url' : '/services/res/catalog',
        	'arguments' : [ 
	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'url', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'layer', 'notNull' : false, 'type' : 'int'}
               ]
        },{
        	'description' : '获取所有属性映射集合',
        	'url' : '/services/res/attributeMappingsg',
        	'arguments' : [ 
	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
               ]
        },{
        	'description' : '通过url获取资源的属性集合',
        	'url' : '/services/res/attributes',
        	'arguments' : [ 
	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'url', 'notNull' : true, 'type' : 'String'}
               ]
        },{
        	'description' : '获取每日推荐数据',
        	'url' : '/services/res/getDailyPushData',
        	'arguments' : [ 
	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'context', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'page', 'notNull' : true, 'type' : 'int'},
	               { 'name' : 'pageSize', 'notNull' : true, 'type' : 'int'}
               ]
        },{
        	'description' : '保存用户标注的重点段落',
        	'url' : '/services/res/saveSection',
        	'arguments' : [ 
	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'context', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'libNum', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'resourceId', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'htmlContent', 'notNull' : true, 'type' : 'String'}
               ]
        },{
        	'description' : '获取某资源某个用户所标注的重点段落',
        	'url' : '/services/res/getMarkedSectionList',
        	'arguments' : [ 
	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'context', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'libNum', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'resourceId', 'notNull' : true, 'type' : 'String'}
               ]
        },{
        	'description' : '获取某个用户标注过的信息资源',
        	'url' : '/services/res/getMarkedResourceList',
        	'arguments' : [ 
	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'context', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'pageNo', 'notNull' : true, 'type' : 'int'},
	               { 'name' : 'pageSize', 'notNull' : true, 'type' : 'int'}
               ]
        }
        
	],
	
/*

'resource.one':[
        		{
        			'description' : '获取指定信息资源的属性信息',
        			'url' : '/services/res/{库编号}/{资源id}/info',
        			'arguments' : [ 
        			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
        			]
        	}],
'resource.content':[
        		{
        			'description' : '获取信息资源的正文内容',
        			'url' : '/services/res/{库编号}/{资源id}/text',
        			'arguments' : [ 
        			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
        			]
        	}],
'resource.attach':[
        		{
        			'description' : '获取信息资源指定附件的文件内容',
        			'url' : '/services/res/{库编号}/{资源id}/attach/{文件Id}',
        			'arguments' : [ 
        			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
	            			]
	            	}]
,'resource.count':[
    		{
    			'description' : '获取信息资源的数量',
    			'url' : '/services/res/count',
    			'arguments' : [ 
    			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
    			    { 'name' : 'libNum', 'notNull' : true, 'type' : 'String'}
            			]
            	}]
,'resource.resourcelist':[
    		{
    			'description' : '通过参数获取资源列表',
    			'url' : '/services/res/resourceList',
    			'arguments' : [ 
    			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
    			    { 'name' : 'libNum', 'notNull' : true, 'type' : 'String'},
    			    { 'name' : 'catalogNum', 'notNull' : true, 'type' : 'String'},
    			    { 'name' : 'searchTitle', 'notNull' : false, 'type' : 'String'},
    			    { 'name' : 'pageNo', 'notNull' : true, 'type' : 'int'},
    			    { 'name' : 'pageSize', 'notNull' : true, 'type' : 'int'}
            			]
            	}]
,'resource.resourceListByUrl':[
                  {
                	  'description' : '通过资源url获取资源列表',
                	  'url' : '/services/res/resourceListByUrl',
                	  'arguments' : [ 
                	                 { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
                	                 { 'name' : 'url', 'notNull' : true, 'type' : 'String'},
                	                 { 'name' : 'pageNo', 'notNull' : true, 'type' : 'int'},
                	                 { 'name' : 'pageSize', 'notNull' : true, 'type' : 'int'}
                        	                 ]
                          }]
,'resource.newResource':[
                       {
                    	   'description' : '新建资源',
                    	   'url' : '/services/res/createResource',
                    	   'arguments' : [ 
                    	                  { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
                    	                  { 'name' : 'channelId', 'notNull' : true, 'type' : 'String'},
                    	                  { 'name' : 'channelName', 'notNull' : true, 'type' : 'String'},
                    	                  { 'name' : 'libName', 'notNull' : true, 'type' : 'String'},
                    	                  { 'name' : 'title', 'notNull' : true, 'type' : 'String'},
                    	                  { 'name' : 'content', 'notNull' : true, 'type' : 'String'}
                            	                  ]
                               }]
,'resource.updateResource':[
                 {
                	 'description' : '更新资源',
                	 'url' : '/services/res/updateResource',
                	 'arguments' : [ 
                	                { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
                	                { 'name' : 'channelName', 'notNull' : true, 'type' : 'String'},
                	                { 'name' : 'libName', 'notNull' : true, 'type' : 'String'},
                	                { 'name' : 'title', 'notNull' : true, 'type' : 'String'},
                	                { 'name' : 'attributes', 'notNull' : true, 'type' : 'String'}
                        	                ]
                         }]
,'resource.resourceListByUrlAndAttributes':[
                    {
                    	'description' : '通过资源url获取资源的详细内容',
                    	'url' : '/services/res/resourceListByUrlAndAttributes',
                    	'arguments' : [ 
                    	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
                    	               { 'name' : 'url', 'notNull' : true, 'type' : 'String'},
                    	               { 'name' : 'attributes', 'notNull' : true, 'type' : 'String'}
                            	               ]
                            }]
,'resource.getDailyPushData':[
                                            {
                                            	'description' : '获取每日推荐数据',
                                            	'url' : '/services/res/getDailyPushData',
                                            	'arguments' : [ 
                                            	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
                                            	               { 'name' : 'memberId', 'notNull' : true, 'type' : 'String'},
                                            	               { 'name' : 'page', 'notNull' : true, 'type' : 'int'},
                                            	               { 'name' : 'pageSize', 'notNull' : true, 'type' : 'int'}
                                            	               ]
                                            }]
,
	'resourceLib.list':[
{
	'description' : '获取正常状态的资源库清单',
	'url' : '/services/resdb/list',
	'arguments' : [ 
	    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
		]
}
],
'resourceLib.detail':[
{
	'description' : '查看资源库详情',
	'url' : '/services/resdb/dbinfo/{资源库编号}',
	'arguments' : [ 
	    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
		]
}
],*/
	'file.convertTxt':[
		{
			'description' : '上传一个文件并转成txt文件',
			'url' : '/services/file/convert/txt/upload',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'file', 'notNull' : true, 'type' : 'file'},
			    { 'name' : 'fileTitle', 'notNull' : false, 'type' : 'String'},
			    { 'name' : 'fileExt', 'notNull' : false, 'type' : 'String'}
			]
	},{
		'description' : '把指定临时文件转成txt文件',
		'url' : '/services/file/convert/txt/upload/{临时文件的唯一标识}',
		'arguments' : [ { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}]
	}
	],
	'file.convertPdf':[
		{
			'description' : '上传一个文件并转成pdf文件',
			'url' : '/services/file/convert/pdf/upload',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'file', 'notNull' : true, 'type' : 'file'},
			    { 'name' : 'fileTitle', 'notNull' : false, 'type' : 'String'},
			    { 'name' : 'fileExt', 'notNull' : false, 'type' : 'String'}
			]
	},{
		'description' : '把指定临时文件转成txt文件',
		'url' : '/services/file/convert/pdf/upload/{临时文件的唯一标识}',
		'arguments' : [ { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}]
	}
	],
	'file.convertHtml':[
		{
			'description' : '上传一个文件并转成html文件',
			'url' : '/services/file/convert/html/upload',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'file', 'notNull' : true, 'type' : 'file'},
			    { 'name' : 'fileTitle', 'notNull' : false, 'type' : 'String'},
			    { 'name' : 'fileExt', 'notNull' : false, 'type' : 'String'}
			]
	},{
		'description' : '把指定临时文件转成txt文件',
		'url' : '/services/file/convert/html/upload/{临时文件的唯一标识}',
		'arguments' : [ { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}]
	}
	],
	'file.convertPic':[
		{
			'description' : '上传一个文件并转成图片文件',
			'url' : '/services/file/convert/pic/upload',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'file', 'notNull' : true, 'type' : 'file'},
			    { 'name' : 'fileTitle', 'notNull' : false, 'type' : 'String'},
			    { 'name' : 'fileExt', 'notNull' : false, 'type' : 'String'},
			    { 'name' : 'startPage', 'notNull' : false, 'type' : 'int'},
			    { 'name' : 'pageSize', 'notNull' : false, 'type' : 'int'}
			]
	},{
		'description' : '把指定临时文件转成txt文件',
		'url' : '/services/file/convert/pic/upload/{临时文件的唯一标识}',
		'arguments' : [ 
			{ 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			{ 'name' : 'startPage', 'notNull' : false, 'type' : 'int'},
			{ 'name' : 'pageSize', 'notNull' : false, 'type' : 'int'}
		]
	}
	],
	'file.convertWord':[
		{
			'description' : '上传一个文件并转成WORD文件',
			'url' : '/services/file/convert/word/upload',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'file', 'notNull' : true, 'type' : 'file'},
			    { 'name' : 'fileTitle', 'notNull' : false, 'type' : 'String'},
			    { 'name' : 'fileExt', 'notNull' : false, 'type' : 'String'},
			]
	},{
		'description' : '把指定临时文件转成WORD文件',
		'url' : '/services/file/convert/word/upload/{临时文件的唯一标识}',
		'arguments' : [ 
			{ 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
		]
	}
	]

};
function debug(e){
	var typeVal = $('legend').attr('typeVal');
	var typeNum = $(e).attr('num');
	window.open('/ExIAServer/tools/debugger.jsp?typeVal='+typeVal+'&typeNum='+typeNum);
}