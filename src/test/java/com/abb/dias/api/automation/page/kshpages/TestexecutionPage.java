package com.abb.dias.api.automation.page.kshpages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.testng.Reporter;

import com.abb.dias.api.automation.core.database.DatabaseUtility;
import com.abb.dias.api.automation.core.diffhmtlhighlighter.FileComparisonHighlighter;
import com.abb.dias.api.automation.core.envmanager.EnvironmentManager;
import com.abb.dias.api.automation.core.excelreader.ExcelReader;
import com.abb.dias.api.automation.core.exlinputdata.InputData;
import com.abb.dias.api.automation.core.log.TestLogger;
import com.abb.dias.api.automation.core.report.ExtentsReport;
import com.abb.dias.api.automation.core.restapiutility.RestApiUtility;
import com.abb.dias.api.automation.core.txtfilewriter.NotepadWriter;
import com.abb.dias.api.automation.core.txtfilewriter.TextfileComparision;
import com.abb.dias.api.automtion.core.jsonreader.JsonReader;

import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class TestexecutionPage extends RestApiUtility {

	DatabaseUtility db = new DatabaseUtility();
	InputData id = new InputData();
	InputData id_1 = new InputData();

	ExcelReader exlConfigReader = new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
	String inputfileName = exlConfigReader.getValuefromConfigExcel("Input Excel Name");
	ExcelReader inputFileReader = new ExcelReader(inputfileName);

	/*
	 * This method is used to execute All the API Services
	 */
	@SuppressWarnings("static-access")
	public void executeApisServices(String testname) throws InterruptedException {

		String cap_output = null;
		String jsonString = null;
		Response response = null;
		JSONObject json = null;

		TestLogger.testMessage("Api services Execution has started");
		Reporter.log(" Api services Execution has started");
		try {
			NotepadWriter.deleteResultfolder();

			// for (int i = 0; i < id.meta_api_name.size(); i++) {
			for (int i = 0; i < id.api_name1.size(); i++) {

				if (!id.api_name1.get(i).isEmpty()) {

					for (int j = 0; j < id.meta_api_name.size(); j++) {

						if (id.api_name1.get(i).equals(id.meta_api_name.get(j))
								& (id.execute.get(i).equalsIgnoreCase("yes"))
								& (id.servicename_column.get(i).equalsIgnoreCase(testname))) {
							int counter_repeatation = 1;

							for (int repeation = 1; repeation <= Integer
									.parseInt(id.no_of_repeatations.get(i)); repeation++) {

								TestLogger.testMessage("The S.No of test case about to execute :"
										+ id.s_no_column.get(i) + " &  Testcase id is: " + id.tc_id.get(i)
										+ " &  Number of repetition " + counter_repeatation);
								Reporter.log("The S.No of test case about to execute :" + id.s_no_column.get(i)
										+ " &  Testcase id is: " + id.tc_id.get(i) + " &  Number of repetition "
										+ counter_repeatation);
								System.out.println("++++the parameterixdd body+++" + id.meta_request_body.get(j));
								Reporter.log("++++the parameterixdd body+++" + id.meta_request_body.get(j));

								jsonString = StringParameterization(id.meta_request_body.get(j),
										id.field1_column.get(i), id.field2_column.get(i), id.field3_column.get(i),
										id.field4_column.get(i), id.field5_column.get(i), id.field6_column.get(i),
										id.field7_column.get(i), id.field8_column.get(i), id.field9_column.get(i),
										id.field10_column.get(i), id.value1_column.get(i), id.value2_column.get(i),
										id.value3_column.get(i), id.value4_column.get(i), id.value5_column.get(i),
										id.value6_column.get(i), id.value7_column.get(i), id.value8_column.get(i),
										id.value9_column.get(i), id.value10_column.get(i));

								TestLogger.testMessage("Parameterized Json Request Body is :: " + jsonString);
								Reporter.log("Parameterized Json Request Body is :: " + jsonString);

								// String endPointUrl = metaInputEndpotintConcatnation(id.meta_endpoint.get(i),
								// id.endpoint_value.get(j));
								System.out.println(
										"++++the end point url before paramaterized+++" + id.meta_endpoint.get(j));
								Reporter.log("++++the end point url before paramaterized+++" + id.meta_endpoint.get(j));
								String endPointUrl = endPointValueParameterization(id.meta_endpoint.get(j),
										id.endpoint_label.get(i), id.endpoint_value.get(i));

								TestLogger.testMessage("Parameterized end point url  is :: " + endPointUrl);
								Reporter.log("Parameterized end point url  is :: " + endPointUrl);

								String parmeterizedUrl = StringParameterization(endPointUrl, id.field1_column.get(i),
										id.field2_column.get(i), id.field3_column.get(i), id.field4_column.get(i),
										id.field5_column.get(i), id.field6_column.get(i), id.field7_column.get(i),
										id.field8_column.get(i), id.field9_column.get(i), id.field10_column.get(i),
										id.value1_column.get(i), id.value2_column.get(i), id.value3_column.get(i),
										id.value4_column.get(i), id.value5_column.get(i), id.value6_column.get(i),
										id.value7_column.get(i), id.value8_column.get(i), id.value9_column.get(i),
										id.value10_column.get(i));

								TestLogger.testMessage("++++++The parameterized url ::++++++ " + parmeterizedUrl);
								Reporter.log("++++++The parameterized url ::++++++ " + parmeterizedUrl);

								response = executeHttpRequest(parmeterizedUrl, jsonString, id.command.get(i), testname);

								ExtentsReport.startTest("The S.No of test case executed is :" + id.s_no_column.get(i)
										+ " &  Testcase id is: " + id.tc_id.get(i) + " &  Number of repetition "
										+ counter_repeatation);

								TestLogger.testMessage("The S.No of test case executed is :" + id.s_no_column.get(i)
										+ " &  Testcase id is: " + id.tc_id.get(i) + " &  Number of repetition "
										+ counter_repeatation);
								Reporter.log("The S.No of test case executed is :" + id.s_no_column.get(i)
										+ " &  Testcase id is: " + id.tc_id.get(i) + " &  Number of repetition "
										+ counter_repeatation);

								if (response.getStatusCode() == Integer.parseInt(id.expected_response_code.get(i))) {

									ExtentsReport.testInfo(
											"The Api respone time is : " + response.getTime() + " Milli seconds");

									// This block is use to capture the any key value from response
									if (id.response_validation.get(i).equalsIgnoreCase("yes")) {

										if (!(id.key1_column.get(i).isEmpty()
												|| id.exp_value1_column.get(i).isEmpty())) {
											System.out.println("first block is executred");
											String validationOne = validateApiResponseFields(
													id.response_datatype.get(j), response, 1, 1, id.key1_column.get(i),
													id.exp_value1_column.get(i));
											System.out.println("first block is executred" + validationOne);
											if (id.exp_value1_column.get(i).equalsIgnoreCase(validationOne)) {
												ExtentsReport.testInfo("The key is : " + id.key1_column.get(i)
														+ " matched with the expected value is :"
														+ id.exp_value1_column.get(i));
											} else {
												ExtentsReport.testError("The key is : " + id.key1_column.get(i)
														+ " ,AND The expected value is :" + id.exp_value1_column.get(i)
														+ " ,Actual value is :" + validationOne);
											}

										}

										if (!(id.key2_column.get(i).isEmpty()
												|| id.exp_value2_column.get(i).isEmpty())) {

											System.out.println("The second block is executed");
											String validationTwo = validateApiResponseFields(
													id.response_datatype.get(j), response, 1, 1, id.key2_column.get(i),
													id.exp_value2_column.get(i));
											System.out.println("first block is executred" + validationTwo);

											if (id.exp_value2_column.get(i).equalsIgnoreCase(validationTwo)) {
												ExtentsReport.testInfo("The key is : " + id.key2_column.get(i)
														+ " matched with the expected value is :"
														+ id.exp_value2_column.get(i));
											} else {
												ExtentsReport.testError("The key is : " + id.key2_column.get(i)
														+ " ,AND The expected value is :" + id.exp_value2_column.get(i)
														+ " ,Actual value is :" + validationTwo);
											}

										}

										if (!(id.key3_column.get(i).isEmpty()
												|| id.exp_value3_column.get(i).isEmpty())) {

											System.out.println("The third block is executed");
											String validationThree = validateApiResponseFields(
													id.response_datatype.get(j), response, 1, 1, id.key3_column.get(i),
													id.exp_value3_column.get(i));
											if (id.exp_value3_column.get(i).equalsIgnoreCase(validationThree)) {
												ExtentsReport.testInfo("The key is : " + id.key3_column.get(i)
														+ " matched with the expected value is :"
														+ id.exp_value3_column.get(i));
											} else {
												ExtentsReport.testError("The key is : " + id.key3_column.get(i)
														+ " ,AND The expected value is :" + id.exp_value3_column.get(i)
														+ "  ,Actual value is :" + validationThree);
											}

										}

										if (!(id.key4_column.get(i).isEmpty()
												|| id.exp_value4_column.get(i).isEmpty())) {

											System.out.println("The fourth block is executed");
											String validationFour = validateApiResponseFields(
													id.response_datatype.get(j), response, 1, 1, id.key4_column.get(i),
													id.exp_value4_column.get(i));
											if (id.exp_value4_column.get(i).equalsIgnoreCase(validationFour)) {
												ExtentsReport.testError("The key is : " + id.key4_column.get(i)
														+ " AND The expected value is :" + id.exp_value4_column.get(i)
														+ "Actual value is :" + validationFour);
											} else {
												ExtentsReport.testError("The key is : " + id.key4_column.get(i)
														+ " ,AND The expected value is :" + id.exp_value4_column.get(i)
														+ " ,Actual value is :" + validationFour);
											}

										}

										if (!(id.key5_column.get(i).isEmpty()
												|| id.exp_value5_column.get(i).isEmpty())) {

											System.out.println("The five block is executed");
											String validationFive = validateApiResponseFields(
													id.response_datatype.get(j), response, 1, 1, id.key5_column.get(i),
													id.exp_value5_column.get(i));
											System.out.println("The five block is executed" + validationFive);

											if (id.exp_value5_column.get(i).equalsIgnoreCase(validationFive)) {
												ExtentsReport.testInfo("The key is : " + id.key5_column.get(i)
														+ " matched with the expected value is :"
														+ id.exp_value5_column.get(i));
											} else {
												ExtentsReport.testError("The key is : " + id.key5_column.get(i)
														+ " ,AND The expected value is :" + id.exp_value5_column.get(i)
														+ " ,Actual value is :" + validationFive);
											}

										}

									}

									if (id.capature_output.get(i).equalsIgnoreCase("yes")) {

										capatureResponseandPassToNextApi1(id.response_datatype.get(j), response, 1, i,
												id.field1_column.get(i + 1), id.field2_column.get(i + 1),
												id.field3_column.get(i + 1), id.field4_column.get(i + 1),
												id.field5_column.get(i + 1), id.field6_column.get(i + 1),
												id.field7_column.get(i + 1), id.field8_column.get(i + 1),
												id.field9_column.get(i + 1), id.field10_column.get(i + 1),
												id.value1_column.get(i + 1), id.value2_column.get(i + 1),
												id.value3_column.get(i + 1), id.value4_column.get(i + 1),
												id.value5_column.get(i + 1), id.value6_column.get(i + 1),
												id.value7_column.get(i + 1), id.value8_column.get(i + 1),
												id.value9_column.get(i + 1), id.value10_column.get(i + 1),
												id.capature1_column.get(i), id.capature2_column.get(i),
												id.capature3_column.get(i), id.capature4_column.get(i),
												id.capature5_column.get(i), id.capature6_column.get(i),
												id.capature7_column.get(i), id.capature8_column.get(i),
												id.capature9_column.get(i), id.capature10_column.get(i));

									}
									if ((id.data_validation.get(i).equalsIgnoreCase("no"))) {

										ExtentsReport.testInfo("The S.No of test case executed is :"
												+ id.s_no_column.get(i) + " &  Testcase id is: " + id.tc_id.get(i)
												+ " &  Number of repetition " + counter_repeatation);
										ExtentsReport.testPasedMessage(id.api_name1.get(i) + " Respone code is  :"
												+ response.getStatusCode() + " VS Expected Response code is :"
												+ id.expected_response_code.get(i));

										TestLogger.testMessage(id.api_name1.get(i) + " Respone code is  :"
												+ response.getStatusCode() + " VS Expected Response code is :"
												+ id.expected_response_code.get(i));
										Reporter.log(id.api_name1.get(i) + " Respone code is  :"
												+ response.getStatusCode() + " VS Expected Response code is :"
												+ id.expected_response_code.get(i));

										NotepadWriter
												.collectRespone(
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation,
														response.asString());

										TestLogger.testMessage("Collected API Response into text files");
										Reporter.log("Collected API Response into text files ");

									}

									if ((id.data_validation.get(i).equalsIgnoreCase("yes"))
											& (!(id.meta_sql_query.get(j).isEmpty()))) {

										Boolean tableEmptyStatus = db.getDBTableEmptyStatus(id.meta_sql_query.get(j));
										System.out.println("****The table is empty****" + tableEmptyStatus);
										// if(tableEmptyStatus==true) {

										NotepadWriter
												.collectRespone(
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation,
														response.asString());

										TestLogger.testMessage("Collected API Response into text files");
										Reporter.log("Collected API Response into text files ");
										if (testname.contains(EnvironmentManager.getKshServiceName())) {
											Boolean validDataType = isApiResponseValid(response, testname);
											if (validDataType) {

												// List<Object> apiresponse =
												// convertApiResponseTokeyValuePairs(response);
												List<String> apiresponse = JsonReader
														.convertApiResponseTokeyValuePairs(response);

												TestLogger.testMessage("Converted API Response into key value pairs: "
														+ id.api_name1.get(i));

												NotepadWriter
														.writerr(apiresponse,
																id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																		+ "_repetition_No" + counter_repeatation,
																"api");

												String sqlString = StringParameterization(id.meta_sql_query.get(j),
														id.field1_column.get(i), id.field2_column.get(i),
														id.field3_column.get(i), id.field4_column.get(i),
														id.field5_column.get(i), id.field6_column.get(i),
														id.field7_column.get(i), id.field8_column.get(i),
														id.field9_column.get(i), id.field10_column.get(i),
														id.value1_column.get(i), id.value2_column.get(i),
														id.value3_column.get(i), id.value4_column.get(i),
														id.value5_column.get(i), id.value6_column.get(i),
														id.value7_column.get(i), id.value8_column.get(i),
														id.value9_column.get(i), id.value10_column.get(i));

												TestLogger.testMessage("sql query is :" + sqlString);

												// List<Object> dbres = db.getDBTableResult(sqlString);

												db.getSQLQueryResult(sqlString,id.api_name1.get(j) + "_s.no" + id.s_no_column.get(i)
												+ "_repetition_No" + counter_repeatation);
											

												//// NotepadWriter.writerr(apiresponse,dbres,id.api_name1.get(j) +
												//// "_s.no"+ id.s_no_column.get(j) + "_repetition_No" +
												//// counter_repeatation,id.api_name1.get(j) + "_s.no"+
												//// id.s_no_column.get(j) + "_repetition_No" + counter_repeatation) ;

												// NotepadWriter.writerr(dbres, id.api_name1.get(i) + "_s.no"
												// + id.s_no_column.get(i) + "_repetition_No" + counter_repeatation,
												// "db");

												//List<Object> apires = convertApiResponseTokeyValuePairs(response);
												//List<String> apires = JsonReader.convertApiResponseTokeyValuePairs(response);


												FileComparisonHighlighter.compareFiles(
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation,
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation);

												TextfileComparision.compareFiles(
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation,
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation);

												Boolean comparetextfiles = NotepadWriter.compareTextfiles(
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation,
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation);

												if (comparetextfiles) {

													ExtentsReport.testInfo("The S.No of test case executed is :"
															+ id.s_no_column.get(i) + " & Testcase id is: "
															+ id.tc_id.get(i) + " & Number of Repetaion:: "
															+ counter_repeatation);
													ExtentsReport.testPasedMessage(id.api_name1.get(i)
															+ " Respone code is  :" + response.getStatusCode()
															+ " VS Expected Response code is :"
															+ id.expected_response_code.get(i)
															+ " &  Content of the API Response and DB Response both files are matched : "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation + " VS "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation);

													TestLogger.testMessage("The S.No of test case executed is :"
															+ id.s_no_column.get(i) + " & Testcase id is: "
															+ id.tc_id.get(i) + " & Number of Repetaion:: "
															+ counter_repeatation);

													Reporter.log("The S.No of test case executed is :"
															+ id.s_no_column.get(i) + " & Testcase id is: "
															+ id.tc_id.get(i) + " & Number of Repetaion:: "
															+ counter_repeatation);

													Reporter.log(id.api_name1.get(i) + " Respone code is  :"
															+ response.getStatusCode()
															+ " VS Expected Response code is :"
															+ id.expected_response_code.get(i)
															+ " &  Content of the API Response and DB Response both files are matched : "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation + " VS "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation);

												} else {

													ExtentsReport.testInfo("The S.No of test case executed is :"
															+ id.s_no_column.get(i) + " & Testcase id is: "
															+ id.tc_id.get(i) + " & Number of Repetaion: "
															+ counter_repeatation);
													ExtentsReport.testFail(id.api_name1.get(i) + " Respone code is  :"
															+ response.getStatusCode()
															+ " VS Expected Response code is :"
															+ id.expected_response_code.get(i)
															+ " &  Content of the API Response and DB Response both files are mismatched : "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation + " VS "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation);
													TestLogger.testMessage("The S.No of test case executed is :"
															+ id.s_no_column.get(i) + " & Testcase id is: "
															+ id.tc_id.get(i) + " & Number of Repetaion: "
															+ counter_repeatation);
													TestLogger.errorMessage(id.api_name1.get(i) + " Respone code is  :"
															+ response.getStatusCode()
															+ " VS Expected Response code is :"
															+ id.expected_response_code.get(i)
															+ " &  Content of the API Response and DB Response both files are mismatched : "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation + " VS "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation);
													Reporter.log(id.api_name1.get(i) + " Respone code is  :"
															+ response.getStatusCode()
															+ " VS Expected Response code is :"
															+ id.expected_response_code.get(i)
															+ " &  Content of the API Response and DB Response both files are mismatched : "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation + " VS "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation);

												}

												/*
												 * System.out.println("***************" + apires.size()); if
												 * (dbres.size() == apires.size()) {
												 * 
												 * int counter = 0; for (int k = 0; k < apires.size(); k++) { if
												 * (dbres.get(k).equals(apires.get(k))) {
												 * 
												 * } else { counter = counter + 1; } }
												 * 
												 * }
												 * 
												 * else {
												 * 
												 * ExtentsReport.testError(
												 * "The api response key value pairs count is not machting with the db result key value pairs"
												 * ); TestLogger.testMessage(
												 * "The api response key value pairs count is not machting with the db result key value pairs"
												 * ); Reporter.log(
												 * "The api response key value pairs count is not machting with the db result key value pairs"
												 * ); }
												 */
											}

										}

										// }

										else {
											ExtentsReport.testFail(
													"The api response Data Type is unexpected and cannot break API Response into key value pairs");
											TestLogger.testMessage(
													"The api response  DataType is unexpected and cannot break API Response into key value pairs");
											Reporter.log(
													"The api response DataType is unexpected and cannot break API Response into key value pairs");

										}
									}
								} else {

									if ((id.data_validation.get(i).equalsIgnoreCase("no"))) {

										ExtentsReport.testInfo("The S.No of test case executed is :"
												+ id.s_no_column.get(i) + " & Testcase id is: " + id.tc_id.get(i)
												+ " & Number of repetition: " + counter_repeatation);
										ExtentsReport.testFail(id.api_name1.get(i) + " Respone code is  :"
												+ response.getStatusCode() + " VS Expected Response code is :"
												+ id.expected_response_code.get(i));
										TestLogger.testMessage("The S.No of test case executed is :"
												+ id.s_no_column.get(i) + " & Testcase id is: " + id.tc_id.get(i)
												+ " & Number of repetition: " + counter_repeatation);
										TestLogger.testMessage(id.api_name1.get(i) + " Respone code is  :"
												+ response.getStatusCode() + " VS Expected Response code is :"
												+ id.expected_response_code.get(i));
										Reporter.log(id.api_name1.get(i) + " Respone code is  :"
												+ response.getStatusCode() + " VS Expected Response code is :"
												+ id.expected_response_code.get(i));

										NotepadWriter
												.collectRespone(
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation,
														response.asString());

									}

									if ((id.data_validation.get(i).equalsIgnoreCase("yes"))
											& (!(id.meta_sql_query.get(j).isEmpty()))) {

										NotepadWriter
												.collectRespone(
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation,
														response.asString());
										Boolean validDataType = isApiResponseValid(response, testname);
										if (testname.contains("ksh")) {
											if (validDataType) {
												List<String> apiresponse = convertApiResponseTokeyValuePairs(response);

												// List<Object> apiresponse =
												// convertApiResponseTokeyValuePairs(response);

												NotepadWriter
														.writerr(apiresponse,
																id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																		+ "_repetition_No" + counter_repeatation,
																"api");

												String sqlString = StringParameterization(id.meta_sql_query.get(j),
														id.field1_column.get(i), id.field2_column.get(i),
														id.field3_column.get(i), id.field4_column.get(i),
														id.field5_column.get(i), id.field6_column.get(i),
														id.field7_column.get(i), id.field8_column.get(i),
														id.field9_column.get(i), id.field10_column.get(i),
														id.value1_column.get(i), id.value2_column.get(i),
														id.value3_column.get(i), id.value4_column.get(i),
														id.value5_column.get(i), id.value6_column.get(i),
														id.value7_column.get(i), id.value8_column.get(i),
														id.value9_column.get(i), id.value10_column.get(i));

												TestLogger.testMessage(sqlString);

												// List<Object> dbres = db.getDBTableResult(sqlString);

												// NotepadWriter.writerr(dbres, id.api_name1.get(i) + "_s.no"
												// + id.s_no_column.get(i) + "_repetition_No" + counter_repeatation,
												// "db");
												List<String> apires = JsonReader.convertApiResponseTokeyValuePairs(response);

											//	List<Object> apires = convertApiResponseTokeyValuePairs(response);

												FileComparisonHighlighter.compareFiles(
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation,
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation);
												TextfileComparision.compareFiles(
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation,
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation);

												Boolean comparetextfiles = NotepadWriter.compareTextfiles(
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation,
														id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																+ "_repetition_No" + counter_repeatation);
												if (comparetextfiles) {
													ExtentsReport.testFail(id.api_name1.get(i) + " Respone code is  :"
															+ response.getStatusCode()
															+ " VS Expected Response code is :"
															+ id.expected_response_code.get(i)
															+ "  &  Content of the API Response and DB Response both files are matched : "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation + " VS "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation);

												} else {
													NotepadWriter.collectRespone(
															id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
																	+ "_repetition_No" + counter_repeatation,
															response.asString());

													ExtentsReport.testInfo("The S.No of test case executed is :"
															+ id.s_no_column.get(i) + " & Testcase id is: "
															+ id.tc_id.get(i) + " & Number of repetition: "
															+ counter_repeatation);
													ExtentsReport.testFail(id.api_name1.get(i) + " Respone code is  :"
															+ response.getStatusCode()
															+ " VS Expected Response code is :"
															+ id.expected_response_code.get(i)
															+ " &  Content of the API Response and DB Response both files are mis matched : "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation + " VS "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation);
													TestLogger.testMessage("The S.No of test case executed is :"
															+ id.s_no_column.get(i) + " & Testcase id is: "
															+ id.tc_id.get(i) + " & Number of repetition: "
															+ counter_repeatation);

													TestLogger.errorMessage(id.api_name1.get(i) + " Respone code is  :"
															+ response.getStatusCode()
															+ " VS Expected Response code is :"
															+ id.expected_response_code.get(i)
															+ " &  Content of the API Response and DB Response both files are mis matched : "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation + " VS "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation);
													Reporter.log(id.api_name1.get(i) + " Respone code is  :"
															+ response.getStatusCode()
															+ " VS Expected Response code is :"
															+ id.expected_response_code.get(i)
															+ " &  Content of the API Response and DB Response both files are mis matched : "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation + " VS "
															+ id.api_name1.get(i) + "_s.no" + id.s_no_column.get(i)
															+ "_repetition_No" + counter_repeatation);

												}

												/*
												 * if (dbres.size() == apires.size()) {
												 * 
												 * int counter = 0; for (int k = 0; k < apires.size(); k++) { if
												 * (dbres.get(k).equals(apires.get(k))) {
												 * 
												 * } else { counter = counter + 1; } }
												 * 
												 * }
												 * 
												 * else {
												 * 
												 * ExtentsReport.testInfo(
												 * "The api response key value pairs count is not machting with the db result key value pairs"
												 * ); TestLogger.errorMessage(
												 * "The api response key value pairs count is not machting with the db result key value pairs"
												 * ); Reporter.log(
												 * "The api response key value pairs count is not machting with the db result key value pairs"
												 * );
												 * 
												 * }
												 */

											}

											else {
												ExtentsReport.testFail(
														"The api response Data Type is unexpected and cannot break API Response into key value pairs");
												TestLogger.testMessage(
														"The api response  DataType is unexpected and cannot break API Response into key value pairs");
												Reporter.log(
														"The api response DataType is unexpected and cannot break API Response into key value pairs");

											}
										}

									}

								}
								counter_repeatation = counter_repeatation + 1;

							}

						}
					}
				}

			}
		} catch (NumberFormatException e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ ", An exception occured while run the kshapi service: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod + "An exception occured while run the kshapi service: "
					+ e.getMessage());

		}

		TestLogger.testMessage("The Execution is finished");
		System.out.println("The Execution is Finished");

	}

	/*
	 * This method is used for request body parameterization * @Param obj:this is
	 * request body of the api
	 * 
	 * @Param f1: field1 value in input data file
	 * 
	 * @Param f2: field2 value in input data file
	 * 
	 * @Param f3: field3 value in input data file
	 * 
	 * @Param f4: field4 value in input data file
	 * 
	 * @Param f5: field5 value in input data file
	 * 
	 * @Param v1: value1 value in input data file
	 * 
	 * @Param v2: value2 value in input data file
	 * 
	 * @Param v3: value3 value in input data file
	 * 
	 * @Param v4: value4 value in input data file
	 * 
	 * @Param v5: value5 value in input data file
	 * 
	 */

	public void extractfeildvalues(String f1, String f2, String f3, String f4, String v1, String v2, String v3,
			String v4, JSONObject obj) {

		try {
			if (!f1.isEmpty()) {
				if (obj.containsKey(f1)) {
					obj.put(f1, v1);
				}
			}
			if (!f2.isEmpty()) {
				if (obj.containsKey(f2)) {
					obj.put(f2, v2);

				}
			}
			if (!f3.isEmpty()) {
				if (obj.containsKey(f3)) {
					obj.put(f3, v3);
				}
			}
			if (!f4.isEmpty()) {
				if (obj.containsKey(f4)) {
					obj.put(f4, v4);
				}
			}
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while extracting field values: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while extracting field values: " + e.getMessage());

		}
	}
	/*
	 * This method is usedd to convert into the API response into key value pairs
	 * 
	 * @ Param apiResponse: this is the api Response
	 * 
	 * 
	 * public List<Object> convertApiResponseTokeyValuePairs(Response apiResponse) {
	 * 
	 * List<Object> sortedList = null; try { List<?> itemsCount =
	 * apiResponse.path("$"); String jsonitem[] = new String[itemsCount.size()]; for
	 * (int a = 0; a < itemsCount.size(); a++) { jsonitem[a] =
	 * itemsCount.get(a).toString(); } String itemvalue = null; String[] itemvalue2
	 * = null; List<String> item = new ArrayList<String>(); for (int b = 0; b <
	 * jsonitem.length; b++) { itemvalue = jsonitem[b].substring(1,
	 * jsonitem[b].length() - 1); itemvalue2 = itemvalue.split(","); for (int c = 0;
	 * c < itemvalue2.length; c++) { item.add(itemvalue2[c]); } } sortedList =
	 * item.stream().sorted().collect(Collectors.toList()); } catch (Exception e) {
	 * 
	 * String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
	 * TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod +
	 * ",An exception occured while converting the api json response into key and values pairs: "
	 * + e.getMessage()); Reporter.log(" Method Name is : " + nameofCurrMethod +
	 * ",An exception occured while converting the api json response into key and values pairs: "
	 * + e.getMessage());
	 * 
	 * } return sortedList;
	 * 
	 * }
	 * 
	 */
	/*
	 * This method is used to veirfy the api response is valid or invalid
	 * 
	 * @ Param apiResponse: this is the api Response
	 */

	public boolean isApiResponseValid(Response apiResponse, String testname) {
		boolean charfound = false;
		String item = null;
		if (testname.contains("ksh")) {
			//// boolean charfound=false;
			// String item=null;
			try {
				item = apiResponse.asString();
				char firstCharacter = item.charAt(0);
				if (firstCharacter == EnvironmentManager.getresponseFirstcharacter().charAt(0)) {
					charfound = true;
				}

			} catch (Exception e) {

				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+ ",An exception occured while converting the api json response into key and values pairs: "
						+ e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+ ",An exception occured while converting the api json response into key and values pairs: "
						+ e.getMessage());
			}
		}
		return charfound;

	}

	/*
	 * This method is used to replace the string value with some other value
	 * 
	 * @Param anyString: provide the string name for parameterzation
	 * 
	 * @Param f1: field1 value in input data file
	 * 
	 * @Param f2: field2 value in input data file
	 * 
	 * @Param f3: field3 value in input data file
	 * 
	 * @Param f4: field4 value in input data file
	 * 
	 * @Param f5: field5 value in input data file
	 * 
	 * @Param v1: value1 value in input data file
	 * 
	 * @Param v2: value2 value in input data file
	 * 
	 * @Param v3: value3 value in input data file
	 * 
	 * @Param v4: value4 value in input data file
	 * 
	 * @Param v5: value5 value in input data file
	 */
	public String StringParameterization(String anystring, String f1, String f2, String f3, String f4, String f5,
			String f6, String f7, String f8, String f9, String f10, String v1, String v2, String v3, String v4,
			String v5, String v6, String v7, String v8, String v9, String v10) {

		String modifiedString = anystring;
		// String modifiedString = null;

		System.out.println("++++the any string is ::" + anystring);
		System.out.println("++++the modified string is ::" + modifiedString);

		try {
			System.out.println("v1" + v1);
			System.out.println("v2" + v2);
			System.out.println("v3" + v3);
			System.out.println("v4" + v4);
			System.out.println("v5" + v5);
			System.out.println("v6" + v6);
			System.out.println("v7" + v7);
			System.out.println("v8" + v8);
			System.out.println("v9" + v9);
			System.out.println("v10" + v10);
			System.out.println("f1" + f1);

			if (!f1.isEmpty()) {
				if (modifiedString.contains(f1)) {
					modifiedString = modifiedString.replace(f1, v1);
					System.out.println("one");
				}
			}
			if (modifiedString.contains(f2)) {
				modifiedString = modifiedString.replace(f2, v2);
				System.out.println("two");
			}
			if (modifiedString.contains(f3)) {
				modifiedString = modifiedString.replace(f3, v3);
				System.out.println("three");

			}
			if (modifiedString.contains(f4)) {
				modifiedString = modifiedString.replace(f4, v4);
				System.out.println("four");
			}
			if (modifiedString.contains(f5)) {
				modifiedString = modifiedString.replace(f5, v5);
				System.out.println("five");
			}
			if (modifiedString.contains(f6)) {
				modifiedString = modifiedString.replace(f6, v6);
				System.out.println("six");

			}
			if (modifiedString.contains(f7)) {
				modifiedString = modifiedString.replace(f7, v7);
				System.out.println("seven");

			}
			if (modifiedString.contains(f8)) {
				modifiedString = modifiedString.replace(f8, v8);
				System.out.println("eight");

			}
			if (modifiedString.contains(f9)) {
				modifiedString = modifiedString.replace(f9, v9);
				System.out.println("nine");

			}
			if (modifiedString.contains(f10)) {
				modifiedString = modifiedString.replace(f10, v10);
				System.out.println("ten");

			}

			System.out.println("++++the modified string is ::" + modifiedString);
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while doing the string parameterization: " + e.getMessage());
			TestLogger.errorMessage("An exception occured while doing the string parameterization: " + e.getMessage());
		}
		return modifiedString;

	}

	/*
	 * This method is used to concat the metadata file endpoint with inputdata file
	 * 
	 * @Param meataEndpoint :End point point API Service in meta data file
	 * 
	 * @Param inputEndpoint : End point value in input data file
	 * 
	 */
	public String metaInputEndpotintConcatnation(String metaEndpoint, String inputEndpoint) {

		String tempinputEndpoint = metaEndpoint;
		try {
			if (!inputEndpoint.isEmpty()) {

				tempinputEndpoint = tempinputEndpoint.concat(inputEndpoint);
			}
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while meta data endpoint concatnation with inputdata file Endpoint value: "
					+ e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while meta data endpoint concatnation with inputdata file Endpoint value: "
					+ e.getMessage());
		}
		return tempinputEndpoint;
	}

	/*
	 * This method is used to replace the string value with some other value
	 * 
	 * @Param endpoint: endpoint column in inputdata excel meta data file
	 * 
	 * @Param endpointlabel: end point label inputdata excel input data sheet
	 * 
	 * @Param endpointvalue:endpoint value label in input data excel input data
	 * sheet
	 */
	public String endPointValueParameterization(String endpoint, String endpointlabel, String endpointvalue) {

		String modifiedString = endpoint;
		System.out.println("***************the end point is**********" + " " + endpoint);
		System.out.println("***************the end point lable is**********" + " " + endpointlabel);
		System.out.println("***************the end point value is**********" + " " + endpointvalue);

		try {

			if (modifiedString.contains(endpointlabel)) {
				if (!endpointvalue.isEmpty()) {
					modifiedString = modifiedString.replace(endpointlabel, endpointvalue);

				}
			}

		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while doing the endpoint value parameterization: " + e.getMessage());
			TestLogger.errorMessage("An exception occured while doing the string parameterization: " + e.getMessage());
		}
		return modifiedString;

	}

	/*
	 * This method capture the response out put based on keys and pass it to next
	 * api
	 * 
	 * @Param output_datatype this is data type of the response comings
	 * 
	 * @Param Response this is response of the api
	 * 
	 * @Param this is index of the keys that going to capature the response value
	 * 
	 * @Param valueindex this is index of the that store value into list
	 * 
	 * @Param field_1 this is field 1 column in the excel input data
	 * 
	 * @Param field_2 this is field 2 column in the excel input data
	 * 
	 * @Param field_3 this is field 3 column in the excel input data
	 * 
	 * @Param field_4 this is field_4 column in the excel input data
	 * 
	 * @Param field_5 this is field_5 column in the excel input data
	 * 
	 * @Param value_1 this is value 1 column in the excel input data
	 * 
	 * @Param value_2 this is value 2 column in the excel input data
	 * 
	 * @Param value_3 this is value 3 column in the excel input data
	 * 
	 * @Param value_4 this is value4 column in the excel input data
	 * 
	 * @Param value_5 this is value_5 column in the excel input data
	 * 
	 * @Param key1 this is capature 1 column in the excel input data
	 * 
	 * @Param key2 this is capature 2 column in the excel input data
	 * 
	 * @Param key3 this is capature 3 column in the excel input data
	 * 
	 * @Param key4 this is capature 4 column in the excel input data
	 * 
	 * @Param key5 this is the capature 5 column in the excel input data
	 * 
	 */

	public void capatureResponseandPassToNextApi1(String output_datatype, Response response, int listindex,
			int valueindex, String field_1, String field_2, String field_3, String field_4, String field_5,
			String field_6, String field_7, String field_8, String field_9, String field_10, String value_1,
			String value_2, String value_3, String value_4, String value_5, String value_6, String value_7,
			String value_8, String value_9, String value_10, String cap1, String cap2, String cap3, String cap4,
			String cap5, String cap6, String cap7, String cap8, String cap9, String cap10) {

		try {

			captureOutPutUsingKey(output_datatype, cap1, listindex, valueindex, response, field_1, field_2, field_3,
					field_4, field_5, field_6, field_7, field_8, field_9, field_10, value_1, value_2, value_3, value_4,
					value_5, value_6, value_7, value_8, value_9, value_10);
			captureOutPutUsingKey(output_datatype, cap2, listindex, valueindex, response, field_1, field_2, field_3,
					field_4, field_5, field_6, field_7, field_8, field_9, field_10, value_1, value_2, value_3, value_4,
					value_5, value_6, value_7, value_8, value_9, value_10);
			captureOutPutUsingKey(output_datatype, cap3, listindex, valueindex, response, field_1, field_2, field_3,
					field_4, field_5, field_6, field_7, field_8, field_9, field_10, value_1, value_2, value_3, value_4,
					value_5, value_6, value_7, value_8, value_9, value_10);
			captureOutPutUsingKey(output_datatype, cap4, listindex, valueindex, response, field_1, field_2, field_3,
					field_4, field_5, field_6, field_7, field_8, field_9, field_10, value_1, value_2, value_3, value_4,
					value_5, value_6, value_7, value_8, value_9, value_10);
			captureOutPutUsingKey(output_datatype, cap5, listindex, valueindex, response, field_1, field_2, field_3,
					field_4, field_5, field_6, field_7, field_8, field_9, field_10, value_1, value_2, value_3, value_4,
					value_5, value_6, value_7, value_8, value_9, value_10);
			captureOutPutUsingKey(output_datatype, cap6, listindex, valueindex, response, field_1, field_2, field_3,
					field_4, field_5, field_6, field_7, field_8, field_9, field_10, value_1, value_2, value_3, value_4,
					value_5, value_6, value_7, value_8, value_9, value_10);
			captureOutPutUsingKey(output_datatype, cap7, listindex, valueindex, response, field_1, field_2, field_3,
					field_4, field_5, field_6, field_7, field_8, field_9, field_10, value_1, value_2, value_3, value_4,
					value_5, value_6, value_7, value_8, value_9, value_10);
			captureOutPutUsingKey(output_datatype, cap8, listindex, valueindex, response, field_1, field_2, field_3,
					field_4, field_5, field_6, field_7, field_8, field_9, field_10, value_1, value_2, value_3, value_4,
					value_5, value_6, value_7, value_8, value_9, value_10);
			captureOutPutUsingKey(output_datatype, cap9, listindex, valueindex, response, field_1, field_2, field_3,
					field_4, field_5, field_6, field_7, field_8, field_9, field_10, value_1, value_2, value_3, value_4,
					value_5, value_6, value_7, value_8, value_9, value_10);
			captureOutPutUsingKey(output_datatype, cap10, listindex, valueindex, response, field_1, field_2, field_3,
					field_4, field_5, field_6, field_7, field_8, field_9, field_10, value_1, value_2, value_3, value_4,
					value_5, value_6, value_7, value_8, value_9, value_10);

		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while capaturing the response output and passing to next api: "
					+ e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while capaturing the response output and passing to next api: "
					+ e.getMessage());
		}

	}

	/*
	 * This method is used to capture the api response by providing key
	 * 
	 * @Param output_datatype : data type of the api response
	 * 
	 * @Param captureItem : key of the value to be capture
	 * 
	 * @Param listindex : index of the api response
	 * 
	 * @Param value index : value of the index
	 * 
	 */

	public void captureOutPutUsingKey(String output_datatype, String captureitem, int listindex, int valueindex,
			Response response, String field_1, String field_2, String field_3, String field_4, String field_5,
			String field_6, String field_7, String field_8, String field_9, String field_10, String value_1,
			String value_2, String value_3, String value_4, String value_5, String value_6, String value_7,
			String value_8, String value_9, String value_10) {

		if (output_datatype.equalsIgnoreCase("List")) {

			System.out.println("about to capture item is" + captureitem);
			System.out.println("list index is " + listindex);
			System.out.println("value index is " + valueindex);
			if (!captureitem.isEmpty()) {
				System.out.println("one");
				List<Map<String, String>> items = response.jsonPath().getList("$");
				System.out.println("two");
				String capature1_value = items.get(listindex).get(captureitem);
				System.out.println("the capature value is" + capature1_value);

				if (!field_1.isEmpty()) {

					System.out.println("the filed1 " + field_1);
					if (captureitem.equals(value_1)) {

						id.value1_column.add(valueindex + 1, capature1_value);
						System.out.println("stored");
					}
				}

				if (!field_2.isEmpty()) {

					if (captureitem.equals(value_2)) {

						id.value2_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_3.isEmpty()) {

					if (captureitem.equals(value_3)) {

						id.value3_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_4.isEmpty()) {

					if (captureitem.equals(value_4)) {

						id.value4_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_5.isEmpty()) {

					if (captureitem.equals(value_5)) {

						id.value5_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_6.isEmpty()) {

					if (captureitem.equals(value_6)) {

						id.value6_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_7.isEmpty()) {

					if (captureitem.equals(value_7)) {

						id.value7_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_8.isEmpty()) {

					if (captureitem.equals(value_8)) {

						id.value8_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_9.isEmpty()) {

					if (captureitem.equals(value_9)) {

						id.value9_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_10.isEmpty()) {

					if (captureitem.equals(value_10)) {

						id.value10_column.add(valueindex + 1, capature1_value);

					}
				}

			}

		}

		if (output_datatype.equalsIgnoreCase("Object")) {

			if (!captureitem.isEmpty()) {

				String capatured_object = response.jsonPath().getJsonObject(captureitem);

				if (!field_1.isEmpty()) {

					if (captureitem.equals(value_1)) {

						id.value1_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_2.isEmpty()) {

					if (captureitem.equals(value_2)) {

						id.value2_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_3.isEmpty()) {

					if (captureitem.equals(value_3)) {

						id.value3_column.add(valueindex + 1, capatured_object);

					}
				}
				if (!field_4.isEmpty()) {

					if (captureitem.equals(value_4)) {

						id.value4_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_5.isEmpty()) {

					if (captureitem.equals(value_5)) {

						id.value5_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_6.isEmpty()) {

					if (captureitem.equals(value_6)) {

						id.value6_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_7.isEmpty()) {

					if (captureitem.equals(value_7)) {

						id.value7_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_8.isEmpty()) {

					if (captureitem.equals(value_8)) {

						id.value8_column.add(valueindex + 1, capatured_object);

					}
				}
				if (!field_9.isEmpty()) {

					if (captureitem.equals(value_9)) {

						id.value9_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_10.isEmpty()) {

					if (captureitem.equals(value_10)) {

						id.value10_column.add(valueindex + 1, capatured_object);

					}
				}

			}
		}

		if (output_datatype.equalsIgnoreCase("String")) {

			if (!captureitem.isEmpty()) {

				String capatured_object = response.asString(); //
				// System.out.println("the string output is "+stringoutput);
				// id.value1_column.add(valueindex + 1, stringoutput);

				// String capatured_object = response.jsonPath().getJsonObject(captureitem);

				if (!field_1.isEmpty()) {

					if (captureitem.equals(value_1)) {

						id.value1_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_2.isEmpty()) {

					if (captureitem.equals(value_2)) {

						id.value2_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_3.isEmpty()) {

					if (captureitem.equals(value_3)) {

						id.value3_column.add(valueindex + 1, capatured_object);

					}
				}
				if (!field_4.isEmpty()) {

					if (captureitem.equals(value_4)) {

						id.value4_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_5.isEmpty()) {

					if (captureitem.equals(value_5)) {

						id.value5_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_6.isEmpty()) {

					if (captureitem.equals(value_6)) {

						id.value6_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_7.isEmpty()) {

					if (captureitem.equals(value_7)) {

						id.value7_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_8.isEmpty()) {

					if (captureitem.equals(value_8)) {

						id.value8_column.add(valueindex + 1, capatured_object);

					}
				}
				if (!field_9.isEmpty()) {

					if (captureitem.equals(value_9)) {

						id.value9_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_10.isEmpty()) {

					if (captureitem.equals(value_10)) {

						id.value10_column.add(valueindex + 1, capatured_object);

					}
				}

			}

			/*
			 * 
			 * if (!field_1.isEmpty()) { // if(key1.equals(field_1)) {
			 * 
			 * String stringoutput = response.asString(); //
			 * System.out.println("the string output is "+stringoutput);
			 * id.value1_column.add(valueindex + 1, stringoutput);
			 * 
			 * }
			 */
		}

	}

	/*
	 * This method is used to validate the api response key value with expected
	 * value
	 * 
	 * @Param output_datatype : Expected data type of the api response
	 * 
	 * @Param response : This is response of the api
	 * 
	 * @Param listindex :index of the list data type
	 * 
	 * @Param valueindex:current loop value
	 * 
	 * @Param Key : key of the api response to validate
	 * 
	 * @Param expected Value: Expected value to be present in api response
	 * 
	 */

	public String validateApiResponseFields(String output_datatype, Response response, int listindex, int valueindex,
			String key, String ExpectedValue) {

		String capturedValue = null;

		try {
			if (output_datatype.equalsIgnoreCase("List")) {

				if (!(key.isEmpty() || ExpectedValue.isEmpty())) {

					List<Map<String, String>> items = response.jsonPath().getList("$");
					String capture_value = items.get(listindex).get(key);
					// if (capture_value.equalsIgnoreCase(ExpectedValue)) {
					capturedValue = capture_value;

					// }
				}
			}

			if (output_datatype.equalsIgnoreCase("Object")) {

				if (!(key.isEmpty() && ExpectedValue.isEmpty())) {
					String capatured_object = response.jsonPath().getJsonObject(key);
					if (ExpectedValue.equalsIgnoreCase(capatured_object))
						capturedValue = capatured_object;
				}
			}

			if (output_datatype.equalsIgnoreCase("String")) {
				if (!ExpectedValue.isEmpty()) {
					String responseString = response.asString(); //
					if (responseString.equalsIgnoreCase(responseString)) {
						capturedValue = responseString;
					}
				}
			}

		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while validating api response fields: " + e.getMessage());
			TestLogger.errorMessage("An exception occured while validating api response fields: " + e.getMessage());
		}

		return capturedValue;

	}

	/*
	 * This method is usedd to convert into the API response into key value pairs
	 * 
	 * @ Param apiResponse: this is the api Response
	 */

	public List<String> convertApiResponseTokeyValuePairs(Response apiResponse) {
		List<String> strList = null;
		try {
			String json = apiResponse.getBody().asString();
			// System.out.println("JSON "+json);
			// TestAPIforKetValue parser = new TestAPIforKetValue(json);
			// System.out.println("Out :::"+parser.getPathList());

			JSONArray jsonarray = new JSONArray(json);
			strList = new ArrayList<String>();

			Iterator<Object> iterator = jsonarray.iterator();
			while (iterator.hasNext()) {
				JSONObject jsonObject = (JSONObject) iterator.next();
				for (String key : jsonObject.keySet()) {
					String tempVar = key + "=" + jsonObject.get(key);
					System.out.println("the json key value pairs extraction is going on");
					System.out.println(":::::::::" + tempVar);
					// System.out.println(key + "=" + jsonObject.get(key));
					strList.add(tempVar);
				}
			}
			System.out.println("Value::: ");
			// strList.forEach(System.out::println);
			Collections.sort(strList);

			strList.forEach(System.out::println);

		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while converting the api json response into key and values pairs: "
					+ e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while converting the api json response into key and values pairs: "
					+ e.getMessage());

		}
		return strList;

	}

}
