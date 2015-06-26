<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="default.tiles">
	<tiles:putAttribute name="body">		
		<!-- BEGIN NAV -->
		<div class="container-fluid navWrapper hidden-xs">
		  <div class="col-lg-2 col-md-2 col-sm-2 floatNav hidden-xs">
		    <nav class="navigation">
		      <!--<a href="#" class="active home"> <span class="icon"><i class="fa fa-home"></i></span> <span class="content">Home</span> </a> -->
		      <a href="#app_nav" class="lightGrey"> <span class="icon"><img src="asset/images/Net_icon.png" /></span> <span class="content">.Net Apps</span> </a>
		      <div class="itemHide second_level" id="app_nav"> <a href="#" class="back"> <i class="icon-chevron-left"></i> </a>
		        <div class="content"> <a href="#" class="activeItem"> <span class="content">Shop</span> </a> </div>
		      </div>
		      <a href="#mobiles_nav" class="active red"> <span class="icon"><i class="fa fa-mobile "></i></span> <span class="content">Mobile Apps</span> </a>
		      <div class="itemHide second_level" id="mobiles_nav"> <a href="#" class="back"> <i class="icon-chevron-left"></i> </a>
		        <div class="content"> <a href="#"> <span class="content">Shop</span> </a> </div>
		      </div>
		      <a href="#other_nav" class="green"> <span class="icon"><i class="fa fa-rocket"></i></span> <span class="content">Other Apps</span> </a>
		      <div class="itemHide second_level" id="other_nav"> <a href="#" class="back"> <i class="icon-chevron-left"></i> </a>
		        <div class="content"> <a href="#"> <span class="content">Popular Movies</span> </a> <a href="#"> <span class="content">Most Viewed Movies</span> </a> <a href="#"> <span class="content">Most Commented Movies</span> </a> <a href="#"> <span class="content">Recent Movies</span> </a> </div>
		      </div>
		    </nav>
		  </div>
		  <div class="col-md-6 col-sm-6 main_nav col-xs-12">
		    <nav>
		      <ul id="multicol-menu" class="nav navbar-nav">
		        <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown">Categories <i class="fa fa-angle-down"></i></a>
		          <ul class="dropdown-menu">
		            <li>
		              <div class="row">
		                <ul class="list-unstyled col-sm-6 col-xs-12">
		                  <li><a href="#" class="underline">Group 1</a></li>
		                  <li><a href="#">test1-1</a></li>
		                  <li><a href="#">test1-2</a></li>
		                  <li><a href="#">test1-3</a></li>
		                  <li><a href="#">test1-4</a></li>
		                  <li><a href="#">test1-5</a></li>
		                  <li><a href="#">test1-6</a></li>
		                </ul>
		                <ul class="list-unstyled col-sm-6 col-xs-12">
		                  <li><a href="#" class="underline">Group 2</a></li>
		                  <li><a href="#">test2-1</a></li>
		                  <li><a href="#">test2-2</a></li>
		                  <li><a href="#">test2-3</a></li>
		                  <li><a href="#">test2-4</a></li>
		                  <li><a href="#">test2-5</a></li>
		                  <li><a href="#">test2-6</a></li>
		                </ul>
		              </div>
		            </li>
		          </ul>
		        </li>
		        <li> <a href="#" class="active">Home</a> </li>
		        <li> <a href="#">New Apps</a> </li>
		      </ul>
		    </nav>
		  </div>
		</div>
		<!-- END NAV -->
		<!-- BEGIN MAIN CONTENT -->
		<div class="mainWrapper  container-fluid">
			<div class="row row-offcanvas row-offcanvas-left" style="padding:0; margin:0">
			<!-- sidebar -->
		    <div class="col-xs-8 col-sm-3 sidebar-offcanvas visible-xs" id="sidebar" role="navigation">
		        <ul class="nav">
		          <li><a href="#" class="login">Login</a></li>	
		          <li><a href="#"><span class="icon"><img src="asset/images/Net_icon_dark.png" /></span> .Net Apps</a></li>
		          <li class="active"><a href="#"><span class="icon"><i class="fa fa-mobile "></i></span> Mobile Apps</a></li>
		          <li><a href="#"><span class="icon"><i class="fa fa-rocket"></i></span> Other Apps</a></li>
		          <li><a href="#">Link 2</a></li>
		          <li><a href="#">Link 3</a></li>              
		        </ul>
		    </div>
		  <div class="col-md-10 col-sm-10 col-xs-12 pull-right mainContent">
		  	<section>
		        <div class="row">
		          <div class="col-sm-8 col-xs-8">
		            <h2>Top Apps</h2>
		          </div>
		          <div class="col-sm-4  col-xs-4">
		            <a href="semore.html"><button class="btn pink-default pull-right seeMore" type="button">See more</button></a>
		          </div>
		        </div>
		        <div class="row"> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/Capture.PNG" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Agreement and Contract Management</a></div>
		                <div class="itemAuthor"><a href="#">Enablon</a></div>
		                <div class="itemDesc">The Enablon ACM solution is built on a centralized secure area for storing and sharing all the company's contracts.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><a href="detail.html"><img src="asset/images/img/Capture1.PNG" class="img-responsive"/></a></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="detail.html">Air Quality Managementt</a></div>
		                <div class="itemAuthor"><a href="#">Enablon</a></div>
		                <div class="itemDesc">Enablon AQS helps companies meet their quality management needs.</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/Capture2.PNG" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Audit and Compliance</a></div>
		                <div class="itemAuthor"><a href="#">Enablon</a></div>
		                <div class="itemDesc">Enablon ACS is a compliance management solution for companies and their suppliers.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/Capture3.PNG" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Authorization &amp; Sign Management</a></div>
		                <div class="itemAuthor"><a href="#">Enablon</a></div>
		                <div class="itemDesc">Enablon ASM is personalized and adapted to each company's specific organization and process</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/Capture2.PNG" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/Capture3.PNG" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img7.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img8.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		        </div>
		    </section>
		    <div class="clearfix"></div>
		    <section>
		        <div class="row">
		          <div class="col-sm-8 col-xs-8">
		            <h2>Tools Apps</h2>
		          </div>
		          <div class="col-sm-4  col-xs-4">
		            <button class="btn pink-default pull-right seeMore" type="button">See more</button>
		          </div>
		        </div>
		        <div class="row"> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img1.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img2.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img3.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img4.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img5.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img6.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img7.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img8.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		        </div>
		    </section>
		    <div class="clearfix"></div>
		    <section>
		        <div class="row">
		          <div class="col-sm-8 col-xs-8">
		            <h2>Comunication Apps</h2>
		          </div>
		          <div class="col-sm-4  col-xs-4">
		            <button class="btn pink-default pull-right seeMore" type="button">See more</button>
		          </div>
		        </div>
		        <div class="row"> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img1.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img2.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img3.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img4.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img5.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img6.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img7.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img8.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		        </div>
		    </section>
		    <div class="clearfix"></div>
		    <section>
		        <div class="row">
		          <div class="col-sm-8 col-xs-8">
		            <h2>Enviromental Apps</h2>
		          </div>
		          <div class="col-sm-4  col-xs-4">
		            <button class="btn pink-default pull-right seeMore" type="button">See more</button>
		          </div>
		        </div>
		        <div class="row"> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img1.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img2.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img3.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img4.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img5.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img6.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img7.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</div>
		              </div>
		              <div class="priceNrate">
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.6"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		          <!-- BEGIN BLOCK ITEM -->
		          <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 itemWrapper">
		            <div class="listItem">
		              <div class="imageItem"><img src="asset/images/img/img8.png" class="img-responsive"/></div>
		              <div class="desciptionItem">
		                <div class="itemName"><a href="#">Lorem ipsum dolor sit amet</a></div>
		                <div class="itemAuthor"><a href="#">Thien Nguyen</a></div>
		                <div class="itemDesc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.</div>
		              </div>
		              <div class="priceNrate"> 
		                <!--  READ ONLY RATING. ADD "data-readOnly = true" -->
		                <!-- Change the value of "data-score" -->
		                <div class="rate" data-score="4.3" data-readOnly = "true"></div>
		                <div class="price">Free</div>
		              </div>
		              <div class="clearfix"></div>
		            </div>
		          </div>
		          <!-- END BLOCK ITEM --> 
		        </div>
		    </section>
		  </div>
		  </div>
		</div>
		<!-- END MAIN CONTENT --> 
		
		<!-- END HTML --> 
		<!-- BEGIN LOAD JAVASCRIPT --> 
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
		<script src="asset/library/bootstrap/js/bootstrap.js"></script> 
		<script src="asset/library/raty/jquery.raty.js"></script> 
		<script src="asset/javascript/sbEx/classie.js"></script>
		<script src="asset/javascript/sbEx/uisearch.js"></script>
		<script>
			new UISearch( document.getElementById( 'sb-search' ) );
		</script>
		<script src="asset/javascript/custom.js"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>