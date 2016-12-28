<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.7/iconfont.css" />
<title>index</title>
</head>
<body>
	<header class="navbar-wrapper">
	<div class="navbar navbar-black">
		<div class="container-fluid cl">
			<a class="logo navbar-logo hidden-xs" href="/aboutHui.shtml">Logo</a>
			<a class="logo navbar-logo-m visible-xs" href="/aboutHui.shtml">H-ui</a>
			<span class="logo navbar-slogan hidden-xs">Description</span> <a
				aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs"
				href="javascript:;"></a>
			<nav id="Hui-nav" class="nav navbar-nav">
			<ul class="cl">
				<li id="Huinav_1"><a href="/index.shtml">Home</a></li>
				<li id="Huinav_2"><a href="/Hui-overview.shtml">home1</a></li>
				<li id="Huinav_3"><a href="/lib/jquery.cookie.js.shtml">home</a>
				</li>
				<li id="Huinav_4"><a href="/ext/list.shtml">home</a></li>
				<li id="Huinav_5"><a href="/cases/index.shtml">home</a></li>
			</ul>
			</nav>
			<nav class="navbar-userbar f-r hidden-xs hidden-sm"> <a
				href="javascript:void(0);" onclick="addFavoritepage();return !1;"
				class="btn btn-primary radius" title="收藏">button</a> <a
				class="btn btn-success radius" target="_blank"
				href="/shareCode.shtml">Password</a> <a
				class="btn btn-success radius juanzeng" target="_blank"
				href="juanzeng.shtml">Logout</a> </nav>
		</div>
	</div>
	</header>
	
	<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span><em></em></li>
			</ul>
		</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
	</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="welcome.html"></iframe>
		</div>
	</div>
</section>
	
</body>
</html>