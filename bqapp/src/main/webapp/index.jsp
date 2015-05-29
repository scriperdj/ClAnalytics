<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<jsp:useBean id = "querybq" class="com.bqapp.QueryBig" />
<% String userData = querybq.getUsers(); %>
<% String productData = querybq.getProducts(); %>
<% String browserData = querybq.getBrowsers(); %>
<% String locationData = querybq.getLocations(); %>
<% String deviceData = querybq.getDevice(); %>
<% String screenData = querybq.getScreen(); %>
<% String categoryData = querybq.getCategories(); %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>
    	
        <meta charset="UTF-8">
        <title>Usage Dashboard</title>   
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        
		<!-- Include the CSS files -->
        <%@ include file="header.jsp" %>
        
    </head>
    
    <body class="fixed-left">
        
                
		<!-- Start right content -->
        <div class="content-page">
        	
			<!-- Start Content here -->
			<!-- ============================================================== -->
            <div class="content">
												
				<div class="row">
					<div class="col-lg-8 portlets">
						<div class="widget">
							<div class="widget-header">
								<h2><i class="icon-chart-pie-1"></i> <strong>Products, Categories and Location</strong> Statics</h2>
								<div class="additional-btn">
									
									  
									 <a href="#" class="widget-popout hidden tt" title="Pop Out/In"><i class="icon-publish"></i></a>
									<a href="#" class="widget-maximize hidden"><i class="icon-resize-full-1"></i></a>
									<a href="#" class="widget-toggle"><i class="icon-down-open-2"></i></a>
									<a href="#" class="widget-close"><i class="icon-cancel-3"></i></a>
								</div>
							</div>
							<div class="widget-content">
								<div class="row stacked">
									<div class="col-sm-5 mini-stats">
										<div id="bar-Categories" class="morris-chart" style="height: 170px;"></div>
										<div class="sales-report-data">
											<span class="pull-left">The God Father </span><span class="pull-right">123456</span>
											<div class="progress progress-xs">
												<div style="width: 78%;" class="progress-bar bg-blue-1"></div>
											</div>
											<div class="clearfix"></div>
											<span class="pull-left">The God Father (1970)</span><span class="pull-right">123456</span>
											<div class="progress progress-xs">
												<div style="width: 22%;" class="progress-bar bg-blue-1"></div>
											</div>
											<div class="clearfix"></div>
										</div>
									</div>
									<div class="col-sm-7">
										<div id="bar-Loc" style="height:370px"></div>
									</div>
								</div>

							</div>
						</div>
												
					</div>
					<div class="col-lg-4 portlets">
						<div class="widget">
							<div class="widget-header">
								<h2><i class="icon-chart-pie-1"></i> <strong>Frequent</strong> Users</h2>
								<div class="additional-btn">
									<a href="#" class="hidden reload"><i class="icon-ccw-1"></i></a>
									<a href="#" class="widget-toggle"><i class="icon-down-open-2"></i></a>
									<a href="#" class="widget-close"><i class="icon-cancel-3"></i></a>
								</div>
							</div>
							<div class="widget-content padding">
								<div id="bar-Usr" style="height: 170px;"></div>
							</div>
						</div>

					</div>
				</div>

				<div class="row">
				
				<div class="col-lg-3 col-md-6">
						<div class="widget">
							<div class="widget-header">
								<h2><i class="icon-chart-pie-1"></i> <strong>Browser</strong> Usage</h2>
								<div class="additional-btn">
									<a href="#" class="hidden reload"><i class="icon-ccw-1"></i></a>
									<a href="#" class="widget-toggle"><i class="icon-down-open-2"></i></a>
									<a href="#" class="widget-close"><i class="icon-cancel-3"></i></a>
								</div>
							</div>
							<div class="widget-content padding">
                        		<div id="donut-Browsers" style="height: 250px;"></div>
							</div>
						</div>
						
				</div>
				<div class="col-lg-3 col-md-6">
						<div class="widget">
							<div class="widget-header">
								<h2><i class="icon-chart-pie-1"></i> <strong>Screen Size</strong> Usage</h2>
								<div class="additional-btn">
									<a href="#" class="hidden reload"><i class="icon-ccw-1"></i></a>
									<a href="#" class="widget-toggle"><i class="icon-down-open-2"></i></a>
									<a href="#" class="widget-close"><i class="icon-cancel-3"></i></a>
								</div>
							</div>
							<div class="widget-content padding">
                        		<div id="donut-Screens" style="height: 250px;"></div>
							</div>
						</div>
				</div>
				<div class="col-lg-3 col-md-6">
						<div class="widget">
							<div class="widget-header">
								<h2><i class="icon-chart-pie-1"></i> <strong>Device</strong> Usage</h2>
								<div class="additional-btn">
									<a href="#" class="hidden reload"><i class="icon-ccw-1"></i></a>
									<a href="#" class="widget-toggle"><i class="icon-down-open-2"></i></a>
									<a href="#" class="widget-close"><i class="icon-cancel-3"></i></a>
								</div>
							</div>
							<div class="widget-content padding">
                        		<div id="donut-Devices" style="height: 250px;"></div>
							</div>
						</div>
					</div>	
				</div>	
					
					
			</div>
			<!-- ============================================================== -->
			<!-- End content here -->
			<!-- ============================================================== -->

        </div>
		<!-- End right content -->

	</div>
	
	<!-- End of page -->
		<!-- the overlay modal element -->
	<div class="md-overlay"></div>
	<!-- End of eoverlay modal -->
	<script>
		var resizefunc = [];
	</script>
	
	<!-- Include the javascript files -->
    <%@ include file="footer.jsp" %>
    
    <!-- Page Specific JS Libraries -->
	
	
	<script src="assets/libs/raphael/raphael-min.js"></script>
	<script src="assets/libs/morrischart/morris.min.js"></script>
	<script src="assets/libs/jquery-knob/jquery.knob.js"></script>
	
	<script>
	new Morris.Bar({
	    element: 'bar-Categories',
	    data: [
			<%= categoryData %>
	      /* {category: 'Books', count: 136},
	      {category: 'Moview', count: 137},
	      {category: 'Laptops', count: 275},
	      {category: 'Tablets', count: 380},
	      {category: 'Phones', count: 655} */
	      
	    ],
	    xkey: 'category',
	    ykeys: ['count'],
	    labels: ['Views'],
	    barRatio: 0.4,
	    xLabelAngle: 35,
	    hideHover: 'auto'
	  });
	new Morris.Bar({
	      element: 'bar-Loc',
	      data: [
			<%= locationData %>
	        /* { y: 'Chennai', a: 952},
	        { y: 'Bangalore', a: 985},
	        { y: 'Coimbatore', a: 955},
	        { y: 'Pondicherry', a: 785 },
	        { y: 'Delhi', a: 700 } */
	      ],
	      xkey: 'y',
	      ykeys: ['a'],
	      redraw: true,
	      labels: ['Visitor'],
	      resize: true,
	      barColors: ['#45B29D'],
	      gridTextColor: ['#777'],
	      gridTextSize: 11,
	      hideHover: 'auto',
	      grid :false
	    });
	new Morris.Bar({
	      element: 'bar-Usr',
	      data: [
			<%= userData %>
	        /* { y: 'User1', a: 952},
	        { y: 'User2', a: 985},
	        { y: 'User3', a: 955},
	        { y: 'User4', a: 785 },
	        { y: 'User5', a: 700 },
	        { y: 'User6', a: 601 },
	        { y: 'User7', a: 421 },
	        { y: 'User8', a: 725 },
	        { y: 'User9', a: 350 },
	        { y: 'User10', a: 120 } */
	      ],
	      xkey: 'y',
	      ykeys: ['a'],
	      redraw: true,
	      labels: ['Visitor'],
	      resize: true,
	      barColors: ['#45B29D'],
	      gridTextColor: ['#777'],
	      gridTextSize: 11,
	      hideHover: 'auto',
	      grid :false
	    });
	new Morris.Donut({
		  element: 'donut-Browsers',
		  resize: true,
		  data: [
			<%= browserData %>
		    /* {label: "Safari", value: 60},
		    {label: "Chrome", value: 10},
		    {label: "Internet Explorer", value: 10},
		    {label: "Firefox", value: 20}, */
		  ],
		  formatter: function (y) { return y + "%" }
		});
	new Morris.Donut({
		  element: 'donut-Screens',
		  resize: true,
		  data: [
			<%= screenData %>
		   /* {label: "1024x728", value: 55},
		    {label: "1700x720", value: 30},
		    {label: "1200x800", value: 20} */
		  ],
		  formatter: function (y) { return y + "%" }
		});
	new Morris.Donut({
		  element: 'donut-Devices',
		  resize: true,
		  data: [
			<%= deviceData %>
		    /* {label: "PC", value: 70},
		    {label: "Laptop", value: 10},
		    {label: "Notebook", value: 15},
		    {label: "Phone", value: 5} */
		  ],
		  formatter: function (y) { return y + "%" }
		});
	</script>
	
	</body>

    
</html>