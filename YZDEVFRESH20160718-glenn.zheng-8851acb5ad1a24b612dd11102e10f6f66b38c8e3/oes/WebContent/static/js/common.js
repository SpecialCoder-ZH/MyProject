String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim = function() {
    return this.replace(/(^\s*)/g, "");
}
String.prototype.rtrim = function() {
    return this.replace(/(\s*$)/g, "");
}

function isInteger (str) {
    var regu = /^[-]{0,1}[0-9]{1,}$/;
    return regu.test(str);
}

/*function trim(str){ 
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
function ltrim(str){ 
    return str.replace(/(^\s*)/g,"");
}
function rtrim(str){ 
    return str.replace(/(\s*$)/g,"");
}*/


function htmlEncode(str) { 
    var s = ""; 
    if (str.length == 0) return ""; 
    s = str.replace(/&/g, "&amp;"); 
    s = s.replace(/</g, "&lt;"); 
    s = s.replace(/>/g, "&gt;"); 
    s = s.replace(/ /g, "&nbsp;"); 
    s = s.replace(/\'/g, "&#39;"); 
    s = s.replace(/\"/g, "&quot;"); 
    s = s.replace(/\n/g, "<br/>"); 
    return s; 
} 

function htmlDecode(str) { 
    var s = ""; 
    if (str.length == 0) return ""; 
    s = str.replace(/&amp;/g, "&"); 
    s = s.replace(/&lt;/g, "<"); 
    s = s.replace(/&gt;/g, ">"); 
    s = s.replace(/&nbsp;/g, " "); 
    s = s.replace(/&#39;/g, "\'"); 
    s = s.replace(/&quot;/g, "\""); 
    s = s.replace(/<br\/>/g, "\n"); 
    return s; 
} 
