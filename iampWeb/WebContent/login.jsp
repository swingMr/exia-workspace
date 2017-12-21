<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<title>�����ǻۻ�֧��ƽ̨-�������̨</title>
<meta charset="GBK">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/fonts/iconfont.css" rel="stylesheet" type="text/css">
<script src="/iamp/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" href="/iamp/js/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="/iamp/js/layui/css/global.css" media="all">
<script type="text/javascript" src="/iamp/js/layui-v2/layui.js"></script>
</head>

<body>
<div class="box">
	<div class="login-top">
		<div class="top-left"><a style="color:#ffffff">�����ǻۻ�֧��ƽ̨-�������̨</a></div>
		<div class="top-right">
			<a href="#">����</a>
		</div>
	</div>
	<div class="login-box">
		<div class="box-left"><img src="image/login-im.png" width="282" height="320" alt=""/></div>
		<div class="box-right">
			<div class="right-title"><span>�û���¼</span><spam>UserLogin</spam></div>
			<div class="login-input">
				<ul>
					<li><input type="text" name="userName" lay-verify="title" autocomplete="off" placeholder="�û���" class="login-name">
					<div class="name-icon"><i class="iconfont  icon-ren1"  style="font-size: 18px;"></i></div></li>
					
					<li><input type="password" name="password" lay-verify="title" autocomplete="off" placeholder="����" class="login-name">
					<div class="name-icon"><i class="iconfont  icon-mima"  style="font-size: 18px;"></i></div></li>
					
				</ul>
				<div class="btn-login" id="loginBtn"><a href="javascript:void(0)" class="" >��¼</a></div>
			</div>
			
			
			</div>
	</div>
</div>

	<div class="footer">
		��Ȩ���� @2016-2026 ������Ϣ�Ƽ��ɷ����޹�˾ ��������Ȩ��
	</div>
<script type="text/javascript" src="js/ThreeWebGL.js"></script> 
<script type="text/javascript" src="js/ThreeExtras.js"></script> 
<script type="text/javascript" src="js/Detector.js"></script> 
<script type="text/javascript" src="js/RequestAnimationFrame.js"></script> 
<script id="vs" type="x-shader/x-vertex">

			varying vec2 vUv;

			void main() {

				vUv = uv;
				gl_Position = projectionMatrix * modelViewMatrix * vec4( position, 1.0 );

			}

		</script> 
<script id="fs" type="x-shader/x-fragment">

			uniform sampler2D map;

			uniform vec3 fogColor;
			uniform float fogNear;
			uniform float fogFar;

			varying vec2 vUv;

			void main() {

				float depth = gl_FragCoord.z / gl_FragCoord.w;
				float fogFactor = smoothstep( fogNear, fogFar, depth );

				gl_FragColor = texture2D( map, vUv );
				gl_FragColor.w *= pow( gl_FragCoord.z, 20.0 );
				gl_FragColor = mix( gl_FragColor, vec4( fogColor, gl_FragColor.w ), fogFactor );

			}

		</script> 
<script type="text/javascript">

			if ( ! Detector.webgl ) Detector.addGetWebGLMessage();

			// Bg gradient

			var canvas = document.createElement( 'canvas' );
			canvas.width = 32;
			canvas.height = window.innerHeight;

			var context = canvas.getContext( '2d' );

			var gradient = context.createLinearGradient( 0, 0, 0, canvas.height );
			gradient.addColorStop(0, "#1C77AC");
			gradient.addColorStop(0.5, "#1C77AC");

			context.fillStyle = gradient;
			context.fillRect(0, 0, canvas.width, canvas.height);

			document.body.style.background = 'url(' + canvas.toDataURL('image/png') + ')';

			// Clouds

			var container;
			var camera, scene, renderer, sky, mesh, geometry, material,
			i, h, color, colors = [], sprite, size, x, y, z;

			var mouseX = 0, mouseY = 0;
			var start_time = new Date().getTime();

			var windowHalfX = window.innerWidth / 2;
			var windowHalfY = window.innerHeight / 2;

			init();
			animate();

			function init() {

				container = document.createElement( 'div' );
				document.body.appendChild( container );

				camera = new THREE.Camera( 30, window.innerWidth / window.innerHeight, 1, 3000 );
				camera.position.z = 6000;

				scene = new THREE.Scene();

				geometry = new THREE.Geometry();

				var texture = THREE.ImageUtils.loadTexture( 'image/cloud10.png' );
				texture.magFilter = THREE.LinearMipMapLinearFilter;
				texture.minFilter = THREE.LinearMipMapLinearFilter;

				var fog = new THREE.Fog( 0x4584b4, - 100, 3000 );

				material = new THREE.MeshShaderMaterial( {

					uniforms: {

						"map": { type: "t", value:2, texture: texture },
						"fogColor" : { type: "c", value: fog.color },
						"fogNear" : { type: "f", value: fog.near },
						"fogFar" : { type: "f", value: fog.far },

					},
					vertexShader: document.getElementById( 'vs' ).textContent,
					fragmentShader: document.getElementById( 'fs' ).textContent,
					depthTest: false

				} );

				var plane = new THREE.Mesh( new THREE.Plane( 64, 64 ) );

				for ( i = 0; i < 8000; i++ ) {

					plane.position.x = Math.random() * 1000 - 500;
					plane.position.y = - Math.random() * Math.random() * 200 - 15;
					plane.position.z = i;
					plane.rotation.z = Math.random() * Math.PI;
					plane.scale.x = plane.scale.y = Math.random() * Math.random() * 1.5 + 0.5;

					GeometryUtils.merge( geometry, plane );

				}

				mesh = new THREE.Mesh( geometry, material );
				scene.addObject( mesh );

				mesh = new THREE.Mesh( geometry, material );
				mesh.position.z = - 8000;
				scene.addObject( mesh );

				renderer = new THREE.WebGLRenderer( { antialias: false } );
				renderer.setSize( window.innerWidth, window.innerHeight );
				container.appendChild( renderer.domElement );

				document.addEventListener( 'mousemove', onDocumentMouseMove, false );
				window.addEventListener( 'resize', onWindowResize, false );

			}

			function onDocumentMouseMove( event ) {

				mouseX = ( event.clientX - windowHalfX ) * 0.25;
				mouseY = ( event.clientY - windowHalfY ) * 0.15;

			}

			function onWindowResize( event ) {

				camera.aspect = window.innerWidth / window.innerHeight;
				camera.updateProjectionMatrix();

				renderer.setSize( window.innerWidth, window.innerHeight );

			}

			function animate() {

				requestAnimationFrame( animate );
				render();

			}

			function render() {

				position = ( ( new Date().getTime() - start_time ) * 0.03 ) % 8000;

				camera.position.x += ( mouseX - camera.target.position.x ) * 0.01;
				camera.position.y += ( - mouseY - camera.target.position.y ) * 0.01;
				camera.position.z = - position + 8000;

				camera.target.position.x = camera.position.x;
				camera.target.position.y = camera.position.y;
				camera.target.position.z = camera.position.z - 1000;

				renderer.render( scene, camera );

			}
		$('#loginBtn').on('click',function() {
			var userName = $('input[name="userName"]').val();
			var password = $('input[name="password"]').val();
			$.ajax({
                type: "POST",
                url:'/iamp/login',
                data:{userName:userName,password:password},// ���formid
                async: false,
                error: function(request) {
                    alert("�����쳣");
                },
                dataType:"json",
                success: function(data) {
                	if(true) {
                		window.location.href='/iamp/';
                	} else {
                		var layer = window.layer;
						layer.open({
				          type: 1
				          ,title:" "
				          ,content: '<div style="padding: 20px 100px;">'+data.msg+'</div>'
				          ,btn: '�ر�'
				          ,btnAlign: 'c' //��ť����
				          ,shade: 0 //����ʾ����
				          ,yes: function(){
				        	  layer.closeAll();
				          }
				        });	
						return;
                	}
                }
            });
		});
		
		var layer;
		layui.use(['layer'], function(){
			  layer = layui.layer;
			  window.layer = layer;
		});
		
		
		</script>
		
	
</body>
</html>