package com.epam.reportportal.example.cucumber6.attributes;



public class Constants {


	public static String feesofFL="10";
	public static String FLemail="regressionfl@crimsoni.com";
	public static String FLCode="";
	public static String IADashBoardLocator = "";
	public static String DeliveryPrepLocator = "";
	public static String AutoAllocationManagement = "";
	public static String INEDLocator="";
	public static int dbretry = 3;


	public static double AdvanceEditing_VIPPremiumPercentage = 0.15;
	public static double AdvanceEditing_NonVIPPremiumPercentage = 0.075;
	public static double NormalEditing_VIPPremiumPercentage = 0.20;
	public static double NormalEditing_NonVIPPremiumPercentage = 0.10;



	public static String testFileName = "test.docx";

	public static String EmailTransactionfile = "emailTransaction.csv";
	public static String VIPtagfile ="VIPeditor.docx";
	public static String VIPfile ="VIP.docx";
	public static String PDFfile = "Test.pdf";
	public static String jpgfile = "rajinikanth.jpg";
	public static String textfile = "test.txt";
	public static String uploadFileLOcator = "//input[@name='qqfile']";
	public static String javaScriptAttribute = "arguments[0].removeAttribute('style')";
	public static String DATA_SPLIT = "\\|";
	public static String AUTOVIP = "AUTOVIP";
	public static String VIPTags="VIP";
	public static String ARWithYesOp="Yes";
	public static String webDbURL = "";
	public static String mySqldriver = "com";
	public static String webDbUserName = "";
	public static String webDbPassword = "!nd";
	public static String PostGreSQLDB = "/qa";
	public static final String PostGresDbUserName = "";
	public static final String PostGresDbPassword = "@123";
	public static final String deleteAssignment="delete fere client_name = ";
	public static final String checkAsnInDB= "select * from cs_assignment where client_name= ";
	public static String SAValidationLocator = "saValidationLocators";
	public static String  FL = "FLLocator";
	public static String  FLRatingComment = "This is automation testing";
	public static String  feedHrTimeTakenValue = "5";
	public static String  feedMinTimeTakenValue = "20";
	public static String ManualAllocation = "manualAllocationLocators";
	public static String AllocatedASNsFLED = "AllocatedASNsFLEDLocators";
	public static String createASNLocator = "createAssignmentLocators";
	public static String FilePrepLocator = "filpPrepLocators";
	public static String MappingandblockingLocator = "MappingandBlockingLocators";

	public static final String OBJECT_SPLIT = "!";

	public static final String macKillDriver = "pkill chrome";
	public static final String macKillBrowser = "pkill Google Chrome";
	public static final String linuxKillDriver = "pkill chrome";
	public static final String WindKillDriver = "Taskkill /IM chromedriver.exe /F";
	public static final String windKillBrowser = "Taskkill /IM chrome.exe /F";
	// Application specific Constants


	public static final int retryCounter= 1;

	public static final int iteration = 2;
	public static final String checkAttribute = "checked";
	public static final String jsConstant = "arguments[0].click();";
	public static String SAValidationTestFileName = "sa.validation.xlsx";
	public static String Exportfile = "manual.allocation.xlsx";
	public static String filePrepExport = "batch.file.prep.xlsx";
	public static String Manuscipttitle="Automation Testing";
	public static String appendServiceName_AE ="_AE-1";
	public static String AutomationAuthor="Automation Author";
	public static String  FLPR = "flprLocators";
	public static String checkFLDeliveryLocators = "checkFL_FLPRDeliveryLocators";
	public static final String AEserviceName = "Advance Editing";
	public static String SEserviceName = "Substantive Editing";
	public static final String NEserviceName = "Normal Editing";
	public static final String NTEserviceName= "Native Editing";
	public static String CEserviceName = "Copy Editing";
	public static final String SCEserviceName = "Single-Check editing";
	public static final String PRserviceName = "Proofreading";
	public static final String TrialTag = "Trial";

	public static final String LifeScience = "A - Life Sciences";
	public static final String PhyscialSciencesandEngineering = "B - Physical Sciences and Engineering";
	public static final String Economicsandbusiness = "C - Economics and Business";
	public static final String ArtHumanitiesAndSocialSciences = "D - The Arts, Humanities, and Social Sciences";
	public static final String MedicalClinicalSciences = "E - Medical/Clinical Sciences";
	public static String CSUAT11MSSQL = "jdbc:sqlserver://192.168.0.157;database=CIPLPROD_UAT11";
	public static String CSUAT11UserName = "ranjan.gupta";
	public static String CSUAT11Password = "cipl@123";
	public static String PMcount = "1500";

	public static String words8000 = "8000";
	public static final String FLDefaultCurrency = "FLDefaultCurrency";
	public static final String ratePerWords = "ratePerWords";

	public static String VIPFL = "AUTOVIP";
	public static final String VIPTag = "VIP";
	public static final String SpecialTag = "Special";
	public static final String VIPandSpecialTag ="VIP and Special";
	public static final String B2BTag="B2B";
	public static final String B2CTag="B2C";
	public static final String B2CandB2BTag="B2C and B2B";
	public static String NormalFL = "FL2900";

	public static String CheckFormatOfRegisterPayment="";
	public static int FileDownloadWait=20;
	public static String FLPROriginalFileFormat= "ForFLPR-1_";

	public static String FileUploadWait="25";
	public static String Wait="30";


}


