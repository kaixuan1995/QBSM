/**
 * 
 */

$(function(){
    $("#main").each(function(){
    	
        var url = "../json/area.json";
        var areaJson;
       
        var temp_html;
        var otype = $(this).find("#selece-type");
        var omessage = $(this).find("#selece-message");
        var orole = $(this).find("#selece-role");
        //初始化种类
        var type = function(){
            $.each(areaJson,function(i,type){
                temp_html+="<option value='"+type.t+"'>"+type.t+"</option>";
            });
            otype.html(temp_html);
            message();
        };
        //赋值类别
        var message = function(){
            temp_html = ""; 
            var n = otype.get(0).selectedIndex;
            $.each(areaJson[n].c,function(i,message){
                temp_html+="<option value='"+message.m+"'>"+message.m+"</option>";
            });
            omessage.html(temp_html);
            role();
        };
        //赋值角色
        var role = function(){
            temp_html = ""; 
            var m = otype.get(0).selectedIndex;
            var n = omessage.get(0).selectedIndex;
            if(typeof(areaJson[m].c[n].d) == ""){
                orole.css("display","none");
            }else{
                orole.css("display","inline");
                $.each(areaJson[m].c[n].d,function(i,role){
                    temp_html+="<option value='"+role.r+"'>"+role.r+"</option>";
                });
                orole.html(temp_html);
            };
        };
        //选择改变类别
        otype.change(function(){
            message();
        });
        //选择类别改变角色
        omessage.change(function(){
            role();
        });
        //获取json数据
        $.getJSON(url,function(data){
            areaJson = data;
            type();
        });
    });
});