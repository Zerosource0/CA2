

function getCompleteInfo()
{
    var tableName = ("#completeInfoTable");

    $.ajax
            ({
                type: 'GET',
                url: 'api/person/complete',
                dataType: "json",
                success: function (result)
                {
                    //alert(result);
                    makeTable(tableName, result);


                }
            });
}

function getPersonFromPhone()
{
    var input=$("#phoneNumber").val();
    var showRes=$("#showPhone");
    
    $.ajax
    ({
        type: 'GET',
        url: 'api/person/phone/' +input,
        dataType: "json",
        success: function(result)
        {
           (showRes).text('first name: '+result.firstName +',  last name: '+result.lastName + ',  email '+result.email);
        }
    });
}
function getPeopleFromHobby()
{
    var input=$("#hobbyname").val();
    
    var tableName=("#hobbyNameTable");
    
    $.ajax
    ({
        type: 'GET',
        url: 'api/person/hobby/' +input,
        dataType: "json",
        success: function(result)
        {
            makeTable(tableName, result);
        }
    });
}

function getPeopleFromCity()
{
    var input=$("#cityname").val();
   
    var tableName=("#cityNameTable");

    $.ajax
    ({
        type: 'GET',
        url: 'api/person/city/' +input,
        dataType: "json",
        success: function(result)
        {
           makeTable(tableName, result);
         
            
        }       
    });
}


function getCountHobbyName()
{
    var input=$("#counthobbyname").val();
    
}
//function getCountHobbyName()

function getZipCodes()
{
    alert("zips are working");
    var tableName = ("#zipCodeTable");

    $.ajax
            ({
                type: 'GET',
                url: 'api/person/zipcodes',
                dataType: "json",
                success: function (result)
                {
                    //alert(result);
                    makeTable(tableName, result);


                }
            });
}

function getCompaniesWithMoreThanEmployees()
{
    var input=$("#morethanxemployees").val();
    
}


function editCompany()
{
    
    var id=$("#EDITcompanyId").val();
    
    var output=("{" + 
    "\"name\" "+": "+"\""+ $("#EDITcompanyName").val() +"\", "+
    "\"cdescription\" "+": "+"\""+$("#EDITcompanyDescription").val() +"\", "+
    "\"cvr\" "+": "+"\""+$("#EDITcompanyCvr").val() +"\", "+
    "\"numEmployees\" "+": "+"\""+$("#EDITcompanyEmployess").val() +"\", "+
    "\"marketValue\" "+": "+"\""+$("#EDITcompanyMarketValue").val() +"\", "+
    "\"email\" "+": "+"\""+$("#EDITcompanyEmail").val() +"\", "+
    "\"city\" "+": "+"\""+$("#EDITcompanyCity").val() +"\", "+
    "\"zipCode\" "+": "+"\""+$("#EDITcompanyZipCode").val() +"\", "+
    "\"street\" "+": "+"\""+$("#EDITcompanyStreet").val() +"\", "+
    "\"additionalInfo\" "+": "+"\""+$("#EDITcompanyAdditionalInfo").val() +"\", "+
    "\"phone\" "+": "+"\""+$("#EDITcompanyPhone").val() +"\", "+
    "\"description\" "+": "+"\""+$("#EDITcompanyPhoneDescription").val() +"\""+
    "}");
    
    $("#showPhone").text(output +id);
   
    $.ajax(
     {  
         type: "PUT",
         url: 'api/company/' + id,
         contentType: "application/json; charset=utf-8",
         dataType: "json",
         data: output,
         success: function(result)
         {
             alert("it works");
         }
         
     });
   
}

function editPerson()
{
    
    var id=$("#personId").val();
    var firstName =$("#EDITfirstNameForm").val();
    var output=("{" + 
    "\"firstName\" "+": "+"\""+ firstName +"\", "+
    "\"lastName\" "+": "+"\""+$("#EDITlastNameForm").val() +"\", "+
    "\"email\" "+": "+"\""+$("#EDITemailForm").val() +"\", "+
    "\"city\" "+": "+"\""+$("#EDITcityForm").val() +"\", "+
    "\"zipCode\" "+": "+"\""+$("#EDITzipCodeForm").val() +"\", "+
    "\"street\" "+": "+"\""+$("#EDITstreetForm").val() +"\", "+
    "\"additionalInfo\" "+": "+"\""+$("#EDITadditionalInfoForm").val() +"\", "+
    "\"phone\" "+": "+"\""+$("#EDITphoneForm").val() +"\", "+
    "\"description\" "+": "+"\""+$("#EDITdescform").val() +"\""+
    "}");
    $("#showPhone").text(output);
    
    $.ajax(
     {  
         type: "PUT",
         url: 'api/person/' + id,
         contentType: "application/json; charset=utf-8",
         dataType: "json",
         data: output,
         success: function()
         {
             alert("it works");
         }
         
     });
   
}

function addCompanyComplex()
{
    
   //var firstName =$("#firstNameForm").val();
    var output=("{" + 
    "\"name\" "+": "+"\""+ $("#companyName").val() +"\", "+
    "\"cdescription\" "+": "+"\""+$("#companyDescription").val() +"\", "+
    "\"cvr\" "+": "+"\""+$("#companyCvr").val() +"\", "+
    "\"numEmployees\" "+": "+"\""+$("#companyEmployess").val() +"\", "+
    "\"marketValue\" "+": "+"\""+$("#companyMarketValue").val() +"\", "+
    "\"email\" "+": "+"\""+$("#companyEmail").val() +"\", "+
    "\"city\" "+": "+"\""+$("#companyCity").val() +"\", "+
    "\"zipCode\" "+": "+"\""+$("#companyZipCode").val() +"\", "+
    "\"street\" "+": "+"\""+$("#companyStreet").val() +"\", "+
    "\"additionalInfo\" "+": "+"\""+$("#companyAdditionalInfo").val() +"\", "+
    "\"phone\" "+": "+"\""+$("#companyPhone").val() +"\", "+
    "\"description\" "+": "+"\""+$("#companyPhoneDescription").val() +"\""+
    "}");
    $("#showPhone").text(output);
    
    $.ajax(
     {  
         type: "POST",
         url: 'api/company',
         contentType: "application/json; charset=utf-8",
         dataType: "json",
         data: output,
         success: function()
         {
             alert("it works");
         }
         
     });
   
}

function addPersonComplex()
{
    
   var firstName =$("#firstNameForm").val();
    var output=("{" + 
    "\"firstName\" "+": "+"\""+ firstName +"\", "+
    "\"lastName\" "+": "+"\""+$("#lastNameForm").val() +"\", "+
    "\"email\" "+": "+"\""+$("#emailForm").val() +"\", "+
    "\"city\" "+": "+"\""+$("#cityForm").val() +"\", "+
    "\"zipCode\" "+": "+"\""+$("#zipCodeForm").val() +"\", "+
    "\"street\" "+": "+"\""+$("#streetForm").val() +"\", "+
    "\"additionalInfo\" "+": "+"\""+$("#additionalInfoForm").val() +"\", "+
    "\"phone\" "+": "+"\""+$("#phoneForm").val() +"\", "+
    "\"description\" "+": "+"\""+$("#descform").val() +"\""+
    "}");
    $("#showPhone").text(output);
    
    $.ajax(
     {  
         type: "POST",
         url: 'api/person',
         contentType: "application/json; charset=utf-8",
         dataType: "json",
         data: output,
         success: function()
         {
             alert("it works");
         }
         
     });
   
}


function makeTable(container, result) 
{
    
    $.each(result, function (i)
    {
        $(container).append("<tr>");
        if (i === 0)
        {
            $.each(this, function (k)
            {
                $(container+" tr:last").append("<td>" + k + "</td>");
            });

        }
        return false;
    });
    $.each(result, function (i)
    {
        $(container).append("<tr>");


        $.each(this, function (k, v)
        {

            $(container+" tr:last").append("<td>" + v + "</td>");

        });
    });
}