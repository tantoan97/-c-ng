<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

//Creating Array for JSON response
$response = array();
 
// Check if we got the field from the user
if (isset($_GET['id']) && isset($_GET['trangthai'])) {
 
    $id = $_GET['id'];
    $trangthai= $_GET['trangthai'];
    // $thietbi= $_GET['thietbi'];
    // $ghichu= $_GET['ghichu'];
    
    // Include data base connect class
	$filepath = realpath (dirname(__FILE__));
	require_once($filepath."/db_connect.php");

	// Connecting to database
    $db = new DB_CONNECT();
 
	// Fire SQL query to update student data by id
    $result = mysql_query("UPDATE student SET trangthai = '$trangthai' WHERE id = '$id'");
 
    // Check for succesfull execution of query and no results found
    if ($result) {
        // successfully updation of trangthai (trangthaierature)
        $response["success"] = 1;
        $response["message"] = "student Data successfully updated.";
 
        // Show JSON response
        echo json_encode($response);
    } else {
 
    }
} else {
    // If required parameter is missing
    $response["success"] = 0;
    $response["message"] = "Parameter(s) are missing. Please check the request";
 
    // Show JSON response
    echo json_encode($response);
}
?>