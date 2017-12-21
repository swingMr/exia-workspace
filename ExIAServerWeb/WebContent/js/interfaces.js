var INTERFACE_TYPES = [ {
	'description' : '��������ӿ�',
	'type' : 'base',
	'children' : [ {
		'description' : 'APP��������',
		'type' : 'base.security'
	},{
		'description' : '��Ա�˻�����',
		'type' : 'base.member'
	},{
		'description' : '��ʱ�������',
		'type' : 'base.task'
	} ]
}, {
	'description' : '����ʶ��&��������',
	'type' : 'ontology',
	'children' : [ {
		'description' : 'ʶ��֪ʶ����',
		'type' : 'ontology.recognise'
	}, {
		'description' : '����֪ʶ����',
		'type' : 'ontology.search'
	}, {
		'description' : '��ȡ��������',
		'type' : 'ontology.quotation'
	}, {
		'description' : '������Ϣ��Դ',
		'type' : 'ontology.textsearch'
	}, {
		'description' : '�������ʴ����',
		'type' : 'ontology.chat'
	}]
}, {
	'description' : '֪ʶ�ھ�&��������',
	'type' : 'calculate',
	'children' : [ {
		'description' : '�����ѯ',
		'type' : 'calculate.search'
	}, {
		'description' : '������չ����',
		'type' : 'calculate.debug'
	}, {
		'description' : '���÷���',
		'type' : 'calculate.services'
	} ]
}, 
{
	'description' : '��Ϣ��Դ��ȡ',
	'type' : 'resourceGet',
	'children' : [ {
		'description' : '��Ϣ��Դ��',
		'type' : 'resourceLib'
	}, {
		'description' : '��Ϣ��Դ',
		'type' : 'resource'
	}]
},
/*{
	'description' : '��Ϣ��Դ',
	'type' : 'resource',
	'children' : [ {
		'description' : '��ȡָ����Ϣ��Դ��������Ϣ',
		'type' : 'resource.one'
	}, {
		'description' : '��ȡ��Ϣ��Դ����������',
		'type' : 'resource.content'
	},{
		'description' : '��ȡ��Ϣ��Դָ���������ļ�����',
		'type' : 'resource.attach'
	},{
		'description' : '��ȡ��Ϣ��Դ������',
		'type' : 'resource.count'
	},{
		'description' : 'ͨ��������ȡ��Դ�б�',
		'type' : 'resource.resourcelist'
	},{
		'description' : 'ͨ����Դurl��ȡ��Դ�б�',
		'type' : 'resource.resourceListByUrl'
	},{
		'description' : '�½���Դ',
		'type' : 'resource.newResource'
	},{
		'description' : '������Դ',
		'type' : 'resource.updateResource'
	},{
		'description' : 'ͨ����Դurl��ȡ��Դ����ϸ����',
		'type' : 'resource.resourceListByUrlAndAttributes'
	}]
},*/
{
	'description' : '�ļ���ʽת������',
	'type' : 'file',
	'children' : [ {
		'description' : '�ϴ������ļ�',
		'type' : 'file.uploadAndDownload'
	}, {
		'description' : '�ļ�ת��TXT',
		'type' : 'file.convertTxt'
	}, {
		'description' : '�ļ�ת��PDF',
		'type' : 'file.convertPdf'
	}, {
		'description' : '�ļ�ת��HTML',
		'type' : 'file.convertHtml'
	}, {
		'description' : '�ļ�ת��ͼƬ',
		'type' : 'file.convertPic'
	}, {
		'description' : '�ļ�ת��WORD',
		'type' : 'file.convertWord'
	} ]
} ];

var INTERFACES = {
	'base.security' : [ {
		'description' : '��ȡ�������Token',
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
		'description' : '���memcached����',
		'url' : '/services/base/security/clearCache',
		'arguments' : [ {
			'name' : 'group',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '��Ȩĳ����Աʹ��APP',
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
		'description' : '��ȡapp��ע��Ա�嵥',
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
		'description' : 'ע���Ա��Ϣ�����й�ע��ǰӦ��',
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
		'description' : '��ȡָ���Ļ�Ա��Ϣ',
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
		'description' : '��֤��Ա����',
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
		'description' : '�޸Ļ�Ա��Ϣ',
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
		'description' : '�޸Ļ�Ա����',
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
		'description' : '��ȡ��ǰ�Ѿ�ע��Ķ�ʱ�����嵥',
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
		'description' : '������ƶ�ʱ���������״̬',
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
		'description' : '�����ı������ȡ��Ҫ��',
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
		'description' : '����HTML�����ı����ݵı���',
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
		'description' : '���ļ�����ȡ��Ϣ����DOC��PDF��HTML��TXT�ļ���ȡ�ṹ������',
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
		'description' : '�Ӵ��ı�����ȡ��Ϣ���Ӵ��ı���������ȡ�ṹ������',
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
		'description' : '��ȡ��������������嵥',
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
		'description' : '��ȡָ�������µ����йؼ���',
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
		'description' : '��ȡָ�������µĸ����嵥',
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
		'description' : '���ݸ������ƻ�ȡ�������',
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
		'description' : '��ȡָ���������ظ���',
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
		'description' : '��ȡ����',
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
		'description' : 'ͨ������ʻ�ȡ���弯��',
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
		'description' : 'ͨ������ʻ�ȡ�����',
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
		'description' : '��ȡ��ر���',
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
		'description' : '��ȡ��ر���',
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
		'description' : '��ȡ�ִ�',
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
		'description' : 'ͨ������Ϳ����ȡ��Ϊ',
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
		'description' : '��ȡ���֪ʶ',
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
		'description' : 'ͨ���ؼ��ʻ�ȡ�������Դ',
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
		'description' : '���ݸ������ƻ�ȡ�������',
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
		'description' : '��ȡ�����������',
		'url' : '/services/ontology/search/getThemeWordsOutputStream',
		'arguments' : []
	} ,{
		'description' : '�ִ�',
		'url' : '/services/ontology/search/getTermsByText',
		'arguments' : [ {
			'name' : 'text',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '��ȡ���Ӹ���',
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
		'description' : '��ȡ�ں�����',
		'url' : '/services/ontology/search/getIntensionalConcepts',
		'arguments' : [ {
			'name' : 'conceptId',
			'notNull' : true,
			'type' : 'String'
		}]
	} ,{
		'description' : '��ȡ����Ŀ���',
		'url' : '/services/ontology/search/getBodysOfObjects',
		'arguments' : [ {
			'name' : 'objectIds',
			'notNull' : true,
			'type' : 'String'
		}]
	} ,{
		'description' : '���ݸ���id���Ϻ͹�ϵ���Ƽ���ϵ��ѯ����',
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
		'description' : 'ͨ�����ֻ�ȡ��ϵ���壬�˷����黺��',
		'url' : '/services/ontology/search/getRelationDefinitionsByNames',
		'arguments' : [ {
			'name' : 'names',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '��ȡĳ�������ʵ��Ƽ������ʻ�',
		'url' : '/services/ontology/search/getRecommendedKeywords',
		'arguments' : [ {
			'name' : 'words',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '���ݹؼ��ʾ�������֪ʶ',
		'url' : '/services/ontology/search/searchConceptDefsOfText',
		'arguments' : [ {
			'name' : 'text',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '��ȡ��Ҫ��',
		'url' : '/services/ontology/search/getRelatedFiveElements',
		'arguments' : [ {
			'name' : 'keywords',
			'notNull' : true,
			'type' : 'String'
		}]
	},{
		'description' : '���ݴ������Ϣ�������������',
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
		'description' : '������Ҫ�ؼ�����Ϣ��Դ',
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
		'description' : 'ȫ�ļ���SQL��������Դ',
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
		'description' : '����������ѯ��Ϣ��Դ',
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
		'description' : 'ȫ�ļ���',
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
		'description' : '��ȡ�ı�ժҪ�������',
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
		'description' : '��ǰ��Ա������˽����Ի���ϵ',
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
		'description' : '�ر�ָ��������Ա�Լ�������Ի�',
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
		'description' : '��ѯ��ǰ��Ա�������֮������ڽ����еĶԻ���Ϣ',
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
		'description' : '��ѯָ���Ի��Ļ�Ա�������֮��������¼��������ʱ��Ӵ�С����',
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
		'description' : '��Ա�������˷�����Ϣ�������˶���Ϣ���ݽ��лظ�',
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
		'description' : '����url',
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
		'description' : '��ȡ���з����б�',
		'url' : '/services/calculate/servicelist',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		} ]
	}, {
		'description' : '��ȡ���������Ϣ',
		'url' : '/services/calculate/info/{��������}',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		} ]
	} ],
	'calculate.debug' : [ {
		'description' : '������չ����',
		'url' : '/services/call/{��������}',
		'arguments' : [ {
			'name' : 'extoken',
			'notNull' : true,
			'type' : 'String'
		} ]
	} ],
	'calculate.services' : [ {
		'description' : '�����ı��ִ�',
		'url' : '/services/calculate/participle',
		'arguments' : [ 
		    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'text', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'tagPart', 'notNull' : false, 'type' : 'Boolean'},
		    { 'name' : 'patten', 'notNull' : false, 'type' : 'int'}
		]
    }, {
    	'description' : '�ؼ��ʺ�ժҪ��ȡ',
		'url' : '/services/calculate/abstract',
		'arguments' : [ 
		    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'title', 'notNull' : false, 'type' : 'String'},
		    { 'name' : 'text', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'keyWordLimit', 'notNull' : false, 'type' : 'int'},
		    { 'name' : 'abstractLength', 'notNull' : false, 'type' : 'int'}
		]
    },{
    	'description' : '�����ı����ݵı�ǩ',
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
    	'description' : '�������������ȡ�ؼ�����',
		'url' : '/services/calculate/keypara',
		'arguments' : [ 
		    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'reqSubject', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'text', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'abstractLength', 'notNull' : false, 'type' : 'Int'}
		]
    }, {
    	'description' : '�ı�����',
		'url' : '/services/calculate/classify',
		'arguments' : [ 
		    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'text', 'notNull' : true, 'type' : 'String'},
		    { 'name' : 'modelName', 'notNull' : true, 'type' : 'String'}
		]
    }],
    'file.uploadAndDownload' : [{
			'description' : '�ϴ�һ����ʱ�ļ�',
			'url' : '/services/file/ud/upload',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'file', 'notNull' : true, 'type' : 'file'},
			    { 'name' : 'fileTitle', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'fileExt', 'notNull' : true, 'type' : 'String'}
			]
	},{
		'description' : '����һ����ʱ�ļ�',
		'url' : '/services/file/ud/download/{��ʱ�ļ���Ψһ��ʶ}',
		'arguments' : []
	}],
	'file.convertPic':[
		{
			'description' : '�ϴ�һ���ļ���ת��ͼƬ�ļ�',
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
		'description' : '��ָ����ʱ�ļ�ת��ͼƬ�ļ�',
		'url' : '/services/file/convert/pic/upload/{��ʱ�ļ���Ψһ��ʶ}',
		'arguments' : []
	}
	],
	'resourceLib':[
		{
			'description' : '��ȡ����״̬����Դ���嵥',
			'url' : '/services/resdb/list',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
			]
		},{
			'description' : '�鿴��Դ������',
			'url' : '/services/resdb/dbinfo/{��Դ����}',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			]
		}
	],
	'resource':[
		{
			'description' : '��ȡָ����Ϣ��Դ��������Ϣ',
			'url' : '/services/res/{����}/{��Դid}/info',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
			]
		},{
			'description' : '��ȡ��Ϣ��Դ����������',
			'url' : '/services/res/{����}/{��Դid}/text',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
			]
		},{
			'description' : '��ȡ��Ϣ��Դָ���������ļ�����',
			'url' : '/services/res/{����}/{��Դid}/attach/{�ļ�Id}',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
			]
		},{
			'description' : '��ȡ��Ϣ��Դ������',
			'url' : '/services/res/count',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'libNum', 'notNull' : true, 'type' : 'String'}
			]
		}, {
	      	  'description' : 'ͨ����Դurl��ȡ��Դ�б�',
	    	  'url' : '/services/res/resourceListByUrl',
	    	  'arguments' : [ 
	                 { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
	                 { 'name' : 'url', 'notNull' : true, 'type' : 'String'},
	                 { 'name' : 'pageNo', 'notNull' : true, 'type' : 'int'},
	                 { 'name' : 'pageSize', 'notNull' : true, 'type' : 'int'}
	             ]
			},{
	        	'description' : 'ͨ����Դurl��ȡ��Դ����ϸ����',
	        	'url' : '/services/res/text',
	        	'arguments' : [ 
		               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
		               { 'name' : 'url', 'notNull' : true, 'type' : 'String'},
		               { 'name' : 'attributes', 'notNull' : true, 'type' : 'String'}
	               ]
	        },{
			'description' : 'ͨ��������ȡ��Դ�б�',
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
     	   'description' : '�½���Դ',
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
	       	 'description' : '������Դ',
	    	 'url' : '/services/res/updateResource',
	    	 'arguments' : [ 
	                { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
	                { 'name' : 'channelName', 'notNull' : true, 'type' : 'String'},
	                { 'name' : 'libName', 'notNull' : true, 'type' : 'String'},
	                { 'name' : 'title', 'notNull' : true, 'type' : 'String'},
	                { 'name' : 'attributes', 'notNull' : true, 'type' : 'String'}
	    	    ]
		},{
        	'description' : '��ȡ��ԴĿ¼',
        	'url' : '/services/res/catalog',
        	'arguments' : [ 
	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'url', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'layer', 'notNull' : false, 'type' : 'int'}
               ]
        },{
        	'description' : '��ȡ��������ӳ�伯��',
        	'url' : '/services/res/attributeMappingsg',
        	'arguments' : [ 
	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
               ]
        },{
        	'description' : 'ͨ��url��ȡ��Դ�����Լ���',
        	'url' : '/services/res/attributes',
        	'arguments' : [ 
	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'url', 'notNull' : true, 'type' : 'String'}
               ]
        },{
        	'description' : '��ȡÿ���Ƽ�����',
        	'url' : '/services/res/getDailyPushData',
        	'arguments' : [ 
	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'context', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'page', 'notNull' : true, 'type' : 'int'},
	               { 'name' : 'pageSize', 'notNull' : true, 'type' : 'int'}
               ]
        },{
        	'description' : '�����û���ע���ص����',
        	'url' : '/services/res/saveSection',
        	'arguments' : [ 
	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'context', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'libNum', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'resourceId', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'htmlContent', 'notNull' : true, 'type' : 'String'}
               ]
        },{
        	'description' : '��ȡĳ��Դĳ���û�����ע���ص����',
        	'url' : '/services/res/getMarkedSectionList',
        	'arguments' : [ 
	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'context', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'libNum', 'notNull' : true, 'type' : 'String'},
	               { 'name' : 'resourceId', 'notNull' : true, 'type' : 'String'}
               ]
        },{
        	'description' : '��ȡĳ���û���ע������Ϣ��Դ',
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
        			'description' : '��ȡָ����Ϣ��Դ��������Ϣ',
        			'url' : '/services/res/{����}/{��Դid}/info',
        			'arguments' : [ 
        			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
        			]
        	}],
'resource.content':[
        		{
        			'description' : '��ȡ��Ϣ��Դ����������',
        			'url' : '/services/res/{����}/{��Դid}/text',
        			'arguments' : [ 
        			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
        			]
        	}],
'resource.attach':[
        		{
        			'description' : '��ȡ��Ϣ��Դָ���������ļ�����',
        			'url' : '/services/res/{����}/{��Դid}/attach/{�ļ�Id}',
        			'arguments' : [ 
        			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
	            			]
	            	}]
,'resource.count':[
    		{
    			'description' : '��ȡ��Ϣ��Դ������',
    			'url' : '/services/res/count',
    			'arguments' : [ 
    			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
    			    { 'name' : 'libNum', 'notNull' : true, 'type' : 'String'}
            			]
            	}]
,'resource.resourcelist':[
    		{
    			'description' : 'ͨ��������ȡ��Դ�б�',
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
                	  'description' : 'ͨ����Դurl��ȡ��Դ�б�',
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
                    	   'description' : '�½���Դ',
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
                	 'description' : '������Դ',
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
                    	'description' : 'ͨ����Դurl��ȡ��Դ����ϸ����',
                    	'url' : '/services/res/resourceListByUrlAndAttributes',
                    	'arguments' : [ 
                    	               { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
                    	               { 'name' : 'url', 'notNull' : true, 'type' : 'String'},
                    	               { 'name' : 'attributes', 'notNull' : true, 'type' : 'String'}
                            	               ]
                            }]
,'resource.getDailyPushData':[
                                            {
                                            	'description' : '��ȡÿ���Ƽ�����',
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
	'description' : '��ȡ����״̬����Դ���嵥',
	'url' : '/services/resdb/list',
	'arguments' : [ 
	    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}
		]
}
],
'resourceLib.detail':[
{
	'description' : '�鿴��Դ������',
	'url' : '/services/resdb/dbinfo/{��Դ����}',
	'arguments' : [ 
	    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
		]
}
],*/
	'file.convertTxt':[
		{
			'description' : '�ϴ�һ���ļ���ת��txt�ļ�',
			'url' : '/services/file/convert/txt/upload',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'file', 'notNull' : true, 'type' : 'file'},
			    { 'name' : 'fileTitle', 'notNull' : false, 'type' : 'String'},
			    { 'name' : 'fileExt', 'notNull' : false, 'type' : 'String'}
			]
	},{
		'description' : '��ָ����ʱ�ļ�ת��txt�ļ�',
		'url' : '/services/file/convert/txt/upload/{��ʱ�ļ���Ψһ��ʶ}',
		'arguments' : [ { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}]
	}
	],
	'file.convertPdf':[
		{
			'description' : '�ϴ�һ���ļ���ת��pdf�ļ�',
			'url' : '/services/file/convert/pdf/upload',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'file', 'notNull' : true, 'type' : 'file'},
			    { 'name' : 'fileTitle', 'notNull' : false, 'type' : 'String'},
			    { 'name' : 'fileExt', 'notNull' : false, 'type' : 'String'}
			]
	},{
		'description' : '��ָ����ʱ�ļ�ת��txt�ļ�',
		'url' : '/services/file/convert/pdf/upload/{��ʱ�ļ���Ψһ��ʶ}',
		'arguments' : [ { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}]
	}
	],
	'file.convertHtml':[
		{
			'description' : '�ϴ�һ���ļ���ת��html�ļ�',
			'url' : '/services/file/convert/html/upload',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'file', 'notNull' : true, 'type' : 'file'},
			    { 'name' : 'fileTitle', 'notNull' : false, 'type' : 'String'},
			    { 'name' : 'fileExt', 'notNull' : false, 'type' : 'String'}
			]
	},{
		'description' : '��ָ����ʱ�ļ�ת��txt�ļ�',
		'url' : '/services/file/convert/html/upload/{��ʱ�ļ���Ψһ��ʶ}',
		'arguments' : [ { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'}]
	}
	],
	'file.convertPic':[
		{
			'description' : '�ϴ�һ���ļ���ת��ͼƬ�ļ�',
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
		'description' : '��ָ����ʱ�ļ�ת��txt�ļ�',
		'url' : '/services/file/convert/pic/upload/{��ʱ�ļ���Ψһ��ʶ}',
		'arguments' : [ 
			{ 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			{ 'name' : 'startPage', 'notNull' : false, 'type' : 'int'},
			{ 'name' : 'pageSize', 'notNull' : false, 'type' : 'int'}
		]
	}
	],
	'file.convertWord':[
		{
			'description' : '�ϴ�һ���ļ���ת��WORD�ļ�',
			'url' : '/services/file/convert/word/upload',
			'arguments' : [ 
			    { 'name' : 'extoken', 'notNull' : true, 'type' : 'String'},
			    { 'name' : 'file', 'notNull' : true, 'type' : 'file'},
			    { 'name' : 'fileTitle', 'notNull' : false, 'type' : 'String'},
			    { 'name' : 'fileExt', 'notNull' : false, 'type' : 'String'},
			]
	},{
		'description' : '��ָ����ʱ�ļ�ת��WORD�ļ�',
		'url' : '/services/file/convert/word/upload/{��ʱ�ļ���Ψһ��ʶ}',
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