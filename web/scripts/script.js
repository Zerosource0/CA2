
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
    alert(input);
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
    alert(input);
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
    alert(input);
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