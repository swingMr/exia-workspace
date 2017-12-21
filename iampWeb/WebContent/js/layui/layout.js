layui.config({
	base: 'js/layui/'
}).use(['element', 'layer', 'navbar', 'tab'], function() {
	var element = layui.element()
	$ = layui.jquery,
		layer = layui.layer,
		navbar = layui.navbar(),
		tab = layui.tab({
			elem: '.layout-nav-card', //璁剧疆閫夐」鍗″鍣�
			contextMenu:true
		});

	//iframe鑷�搴�
	$(window).on('resize', function() {
		var $content = $('.layout-nav-card .layui-tab-content');
		$content.height($(this).height() - 165);
		$content.find('iframe').each(function() {
			$(this).height($content.height());
		});
	}).resize();

	var $menu = $('#menu');
	$menu.find('li.layui-nav-item').each(function() {
		var $this = $(this);
		//缁戝畾涓�骇瀵艰埅鐨勭偣鍑讳簨浠�
		$this.on('click', function() {
			//鑾峰彇璁剧疆鐨勬ā鍧桰D
			var id = $this.find('a').data('module-id');
			//杩欓噷鐨勬暟鎹簮鍙槸婕旂ず鏃剁敤鐨勶紝瀹為檯闇�眰鍙兘閫氳繃杩滅▼璇诲彇锛堟牴鎹ā鍧桰D鏉ヨ幏鍙栧搴旀ā鍧楃殑淇℃伅锛�
			var url;
			switch(id) {
				case 1:
					url = 'datas/nav_content.json';
					break;
				case 3:
					url = 'datas/nav_member.json';
					break;
				default:
					break;
			}
			//璁剧疆鏁版嵁婧愭湁涓や釜鏂瑰紡銆�
			//绗竴锛氬湪姝ら〉闈㈤�杩嘺jax璇诲彇璁剧疆  涓句釜鏍楀瓙锛�
			//---------杩欐槸绗竴涓牀瀛�---------
			/*$.getJSON('/api/xxx',{moduleId:id},function(data){
				navbar.set({
					elem: '#side',
					data: data
				});
				navbar.render();
				navbar.on('click(side)', function(data) {
					tab.tabAdd(data.field);
				});
			});*/
			//------------鏍楀瓙缁撴潫--------------
			//绗簩锛氳缃畊rl
			//---------杩欐槸绗簩涓牀瀛�---------
			/*navbar.set({
				elem: '#side',
				url: '/api/xxx?moduleId='+id
			});
			navbar.render();
			navbar.on('click(side)', function(data) {
				tab.tabAdd(data.field);
			});*/
			//------------鏍楀瓙缁撴潫--------------	

			//璁剧疆navbar
			navbar.set({
				elem: '#side', //瀛樺湪navbar鏁版嵁鐨勫鍣↖D
				url: url
			});
			//娓叉煋navbar
			navbar.render();
			//鐩戝惉鐐瑰嚮浜嬩欢
			navbar.on('click(side)', function(data) {
				layer.msg(data.field.href);
				tab.tabAdd(data.field);
			});
		});

	});
	//妯℃嫙鐐瑰嚮鍐呭绠＄悊
	$('.beg-layout-menu').find('a[data-module-id=1]').click();

	element.on('nav(user)', function(data) {
		var $a = data.children('a');
		if($a.data('tab') !== undefined && $a.data('tab')) {
			tab.tabAdd({
				title: $a.children('cite').text(),
				//icon: 'fa-user',
				href: $a.data('url')
			});
		}
	});

	$('.beg-layout-side-toggle').on('click', function() {
		var sideWidth = $('.beg-layout-side').width();
		if(sideWidth === 200) {
			$('.beg-layout-body').animate({
				left: '0'
			});
			$('.beg-layout-footer').animate({
				left: '0'
			});
			$('.beg-layout-side').animate({
				width: '0'
			});
		} else {
			$('.beg-layout-body').animate({
				left: '200px'
			});
			$('.beg-layout-footer').animate({
				left: '200px'
			});
			$('.beg-layout-side').animate({
				width: '200px'
			});
		}
	});
});