<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title></title>
    <style type="text/css">
    .content-wrapper {
    background: #F4F5F7;
    padding: 1.5rem 2.187rem 1.5rem 3.5rem;
    width: 123%;
    -webkit-flex-grow: 1;
    flex-grow: 1;
}
    
    </style>
    <!-- plugins:css -->
    <link rel="stylesheet" href="asset/vendors/feather/feather.css">
    <link rel="stylesheet" href="asset/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="asset/vendors/ti-icons/css/themify-icons.css">
    <link rel="stylesheet" href="asset/vendors/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="asset/vendors/typicons/typicons.css">
    <link rel="stylesheet" href="asset/vendors/simple-line-icons/css/simple-line-icons.css">
    <link rel="stylesheet" href="asset/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="asset/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css">
     <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <link rel="stylesheet" href="asset/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
    <link rel="stylesheet" type="text/css" href="asset/js/select.dataTables.min.css">
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="asset/css/style.css">
    <!-- endinject -->
    <link rel="shortcut icon" href="asset/images/favicon.png" />
  </head>
  <body class="with-welcome-text">
    <div class="container-scroller">
    
      
      <!-- partial:partials/_navbar.html -->
      <%@include file="Navbar.jsp" %>
       
      <!-- partial -->
      <div class="container-fluid page-body-wrapper">
        <!-- partial:partials/_sidebar.html -->
         
       
        <!-- partial -->
        <div class="main-panel">
          <div class="content-wrapper" style="width: 123%" >
            <div class="row">
              <div class="col-sm-12">
                <div class="home-tab">
                 <!--  <div class="d-sm-flex align-items-center justify-content-between border-bottom">
                    <ul class="nav nav-tabs" role="tablist">
                      <li class="nav-item">
                        <a class="nav-link active ps-0" id="home-tab" data-bs-toggle="tab" href="#overview" role="tab" aria-controls="overview" aria-selected="true">Massages</a>
                      </li>
                      
                    </ul>
                    
                  </div> -->
                  <div class="tab-content tab-content-basic">
                    <div class="tab-pane fade show active" id="overview" role="tabpanel" aria-labelledby="overview">
                      
                   
                      <div class="row">
                      
                        <div class="row">
                     
              <div class="col-md-4 stretch-card grid-margin">
            
                <div class="card bg-gradient-danger card-img-holder text-white">
                  <div class="card-body">
                   
                    <h4 class="font-weight-normal mb-3">Transaction Total <i class="mdi mdi-chart-line mdi-24px float-end"></i>
                    </h4>
                    <h2 class="mb-5">${totalAmounts}</h2>
                       <h6 class="card-text">Total Transaction:${listTransaction.size()}</h6>
                     <a href="listalltransaction">View</a>
                  </div>
                </div>
                
              </div>
              
              <div class="col-md-4 stretch-card grid-margin">
                <div class="card bg-gradient-info card-img-holder text-white">
                  <div class="card-body">
                   
                    <h4 class="font-weight-normal mb-3">Total customer <i class="mdi mdi-bookmark-outline mdi-24px float-end"></i>
                    </h4>
                    <h2 class="mb-5">${listAllcustomer.size()}</h2>
                     <h6 class="card-text">Increased by 60%</h6>
                   
                  </div>
                </div>
              </div>
              <div class="col-md-4 stretch-card grid-margin">
                <div class="card bg-gradient-success card-img-holder text-white">
                  <div class="card-body">
                  
                    <h4 class="font-weight-normal mb-3">Today Transaction <i class="mdi mdi-diamond mdi-24px float-end"></i>
                  </h4>
                    <h2 class="mb-5">${totalAmount}</h2>
                    <h6 class="card-text">Today Transaction:${gsttansaction.size()}</h6>
                  </div>
                </div>
              </div>
            </div>
           
                  
                        <div class="col-lg-4 d-flex flex-column">
                          
                        
                       <!--   
                          <div class="row flex-grow">
                            <div class="col-12 grid-margin stretch-card">
                              <div class="card card-rounded">
                                <div class="card-body">
                                  <div class="row">
                                    <div class="col-lg-12">
                                      <div class="d-flex justify-content-between align-items-center mb-3">
                                        <div>
                                          <h4 class="card-title card-title-dash">Last Massage</h4>
                                        </div>
                                      </div>
                                      
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div> -->
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- content-wrapper ends -->
          <!-- partial:partials/_footer.html -->
         
          <!-- partial -->
        </div>
        <!-- main-panel ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    <!-- plugins:js -->
    <script src="vendors/js/vendor.bundle.base.js"></script>
    <script src="asset/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <script src="asset/vendors/chart.js/chart.umd.js"></script>
    <script src="asset/vendors/progressbar.js/progressbar.min.js"></script>
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="asset/js/off-canvas.js"></script>
    <script src="asset/js/template.js"></script>
    <script src="asset/js/settings.js"></script>
    <script src="asset/js/hoverable-collapse.js"></script>
    <script src="asset/js/todolist.js"></script>
    <!-- endinject -->
    <!-- Custom js for this page-->
    <script src="asset/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="asset/js/dashboard.js"></script>
    <!-- <script src="asset/js/Chart.roundedBarCharts.js"></script> -->
    <!-- End custom js for this page-->
  </body>
  
  
</html>