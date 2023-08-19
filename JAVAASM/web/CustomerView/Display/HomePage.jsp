<%-- 
    Document   : HomePage
    Created on : Mar 24, 2022, 11:12:15 AM
    Author     : TingLe
--%>

<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Category"%>
<%@page import="Model.Product"%>
<%@page import="Model.Status"%>
<%@page import="Model.ProductDA"%>
<%@page import="Model.CapacityDA"%>
<%@page import="Model.CategoryDA"%>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/24c9630061.js" crossorigin="anonymous"></script>
        <link href="homepage.css" rel="stylesheet" type="text/css"/>
        <style>
            .btn-dark:hover{
                background-color: white;
                color: black;
                transition: .5s;
            }    
            .btn-primary:hover{
                background-color: white;
                color: blue;
                transition: .5s;
            } 
            .card-img-top{
                width:70%;
                margin-left:15%;
                margin-top:5%;
            }
            input { 
                border: none;
                text-align: center;
                font-weight: 500;
                outline: none;
            }
            .text-primary input{
                width:10%;
                text-align: left;
            }
            .no-bold{
                font-weight:normal;
            }
            .right-price{
                text-align: right;
            }
            .text-primary input{
                color: #0d6efd!important;
            }    
            .d-flex input{
                width: 20%;
                padding: 0% 1%;
            }
        </style>
    </head>
    <body>
        <%
            //Display All Product Records From DB
            Product p = new Product();
            ProductDA prodDA = new ProductDA();
            CapacityDA capDA = new CapacityDA();
            CategoryDA catDA = new CategoryDA();
            request.setAttribute("product", prodDA.GetAllProduct(1));
            request.setAttribute("capacity", capDA.GetAllCapacity());
            request.setAttribute("category", catDA.GetAllCategory());

            String loginID = (String) (request.getSession()).getAttribute("loginID");

        %>

        <!-- Top Navigation Part -->
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
            <a class="navbar-brand" href="#">Logo</a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="HomePage.jsp?cat=all">Home</a>
                </li>
                <c:forEach items="${category}" var="c">
                    <li class="nav-item">
                        <a class="nav-link" href="HomePage.jsp?cat=${c.catID}">${c.catName}</a>
                    </li>    
                </c:forEach>
            </ul>
            <ul class="navbar-nav" style="margin-left: auto">
                <li class="nav-item">
                    <a class="nav-link" href="../../AdminView/Form/AccountDetailsForm.jsp?loginID=<%=loginID%>">My Account Details</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/JAVAASM/Controller/UserServlet/logout">Logout</a>
                </li>
            </ul>
        </nav>
        <!-- End of Top Navigation Part -->

        <!-- Product Part -->
        <div class="container-fluid" style="padding: 3% 0%;">
            <!-- Section -->
            <section style="background:black;">
                <!-- Container -->
                <div class="container py-5">
                    <div class="row">
                        <!-- Start of Product Details --><% int count = 0; %>
                        <c:forEach items="${product}" var="p">
                            <% request.setAttribute("cat", (String) request.getParameter("cat"));%>
                            <div class="col-md-6 col-lg-6 mb-4 mb-lg-0" style="display: ${p.category.catID == cat || cat == "all" ? "" : "none"}" >

                                <div class="card text-black">
                                    <img src="${pageContext.request.contextPath}/DisplayImgServlet?prodID=${p.prodID}" class="card-img-top"/>
                                    <div class="card-body">
                                        <div class="text-center mt-1">
                                            <h4 class="card-title"><input type="text" value="${p.prodName}" id="prodName[<%=count%>]" readonly/></h4>
                                            <h6 class="text-primary mb-1 pb-3"><span style="margin-left: 4%;">Starting at RM</span><input type="text" value="${p.price}" id="prodPrice[<%=count%>]" readonly/></h6>
                                        </div>

                                        <div class="text-center">
                                            <div class="p-3 mx-n2 mb-4" style="background-color: #eff1f2;">
                                                <h5 class="mb-0">Quick Look</h5>
                                            </div>

                                            <div class="d-flex flex-column mb-4">
                                                <span class="h2 mb-0">${p.desc.split(",")[2]}″</span>
                                                <span><input type="text" value="${p.desc.split(",")[1]}" id="prodCamera[<%=count%>]" class="no-bold" style="width:100%;" readonly/></span>
                                            </div>

                                            <div class="d-flex flex-column mb-4">
                                                <span class="h1 mb-0">
                                                    <i class="fas fa-camera-retro"></i>
                                                </span>
                                                <ul class="list-unstyled mb-0">
                                                    <li aria-hidden="true">—</li>
                                                    <li>${p.desc.split(",")[0]}</li>
                                                    <li aria-hidden="true">—</li>
                                                </ul>
                                            </div>

                                            <div class="d-flex flex-column mb-4">
                                                <span class="h2 mb-0">${p.desc.split(",")[3]}</span>
                                                <span>hours video playback</span>
                                            </div>

                                            <div class="p-3 mx-n2 mb-4" style="background-color: #eff1f2;">
                                                <h5 class="mb-0">Capacity</h5>
                                            </div>

                                            <div class="d-flex flex-column mb-4 lead">
                                                <c:forEach items="${capacity}" var="c">
                                                    <span class="mb-2">${c.capacity} GB</span>
                                                </c:forEach>
                                            </div>
                                        </div>

                                        <form action="DisplayCart.jsp" method="POST">
                                            <div class="d-flex flex-row">
                                                <button type="form" class="btn btn-primary flex-fill ms-0">Add to Cart</button>   
                                                <button type="button" class="btn btn-dark flex-fill ms-5" data-toggle="modal" data-target="#buy" onclick="GetData('${p.prodID}',<%=count%>)">Buy now</button>
                                                <input type="hidden" id="count" value="<%=count++%>"/>
                                            </div>     
                                        </form>     

                                    </div>
                                </div><br/><br/><br/>
                            </div>
                        </c:forEach>
                        <!-- End of Product Details -->
                    </div>
                </div>
                <!-- End of Container -->
            </section>
            <!-- End of Section -->
        </div>
        <!-- End of Product Part -->

        <!-- Pop Up Modal -->  
        <div class="modal fade" id="buy" tabindex="-1" role="dialog" aria-labelledby="buyTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">

                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Product Details</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body" style="background:#eee;">
                        <div class="card text-black">
                            <i class="fab fa-apple fa-lg pt-3 pb-1 px-3"></i>
                            <img src="" class="card-img-top" style="width:55%;margin-left:23%;" id="modal-img"/>
                            <div class="card-body">
                                <div class="text-center">
                                    <h5 class="card-title"><input type="text" id="pName" readonly/></h5>
                                    <p class="text-muted mb-4"><input type="text" id="pCamera" class="no-bold" style="width:100%;color:gray;" readonly/></p>
                                </div>
                                <div >
                                    <div class="d-flex justify-content-between">
                                        <span>Price</span><span class="right-price">RM&nbsp;&nbsp;<input type="text" id="pPrice" class="no-bold right-price" readonly/>.00</span>
                                    </div>
                                    <div class="d-flex justify-content-between" >
                                        <span>Add Ons</span><span class="right-price">RM&nbsp;&nbsp;<input type="text" id="pAddOns" class="no-bold right-price"  value="0" readonly/>.00</span>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <span>Capacity</span>
                                        <span class="right-price" style="width:60%">GB
                                            <select id="capacity" name="capacity" style="border:none;outline:none;margin-left:10%" onclick="getAddOns(this.value)">
                                                <c:forEach items="${capacity}" var="cap">
                                                    <option value="${cap.addOns}">${cap.capacity}</option>
                                                </c:forEach>
                                            </select>
                                        </span>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-between total font-weight-bold mt-4">
                                    <span>Total</span><span class="right-price">RM<input type="text" id="total" class="no-bold right-price" style="font-weight:bold;" readonly/>.00</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal" style="margin-right: 48%;">Close</button>
                        <button type="button" class="btn btn-dark">Proceed to Payment</button>
                    </div>

                </div>
            </div>
        </div>
        <!-- End of Pop Up Modal -->



        <!-- Footer -->
        <!-- End of Footer -->

    </body>
    <script>
        $('#myModal').on('shown.bs.modal', function () {
            $('#myInput').trigger('focus')
        })
        function GetData(pid, count) {
            var id = pid;
            var c = count;
            var name = document.getElementById("prodName[" + c + "]").value;
            var price = document.getElementById("prodPrice[" + c + "]").value;
            var camera = document.getElementById("prodCamera[" + c + "]").value;
            document.getElementById("modal-img").src = "${pageContext.request.contextPath}/DisplayImgServlet?prodID=" + id;
            document.getElementById("pName").value = name;
            document.getElementById("pPrice").value = price;
            document.getElementById("pCamera").value = camera;
            document.getElementById("total").value = parseInt(price);
            console.log(id);
            console.log(c);
            console.log(name);
            console.log(price);
            console.log(camera);
        }
        function getAddOns(addOns) {
            var ao = addOns;
            var price = document.getElementById("pPrice").value;
            var total = parseInt(ao) + parseInt(price);
            document.getElementById("pAddOns").value = ao;
            document.getElementById("total").value = parseInt(total);
            console.log(ao);
        }
    </script>
</html>