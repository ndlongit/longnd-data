<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<table width="70%" border="1">
	<thead>
		<tr>
			<th>ID</th>
			<th><spring:message code="label.logo"/></th>
			<th><spring:message code="label.provider"/></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${dataList}" var="obj" varStatus="status">
		<tr>
			<td>${obj.id}</td>
			<td><c:out value="${obj.logo}"/></td>
			<td><c:out value="${obj.provider}"/></td>
		</tr>
	</c:forEach>
	</tbody>
</table>

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