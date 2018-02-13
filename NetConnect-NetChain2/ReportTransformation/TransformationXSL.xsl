<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">  


 <xsl:template match="/">
<html>
<head>
<style>
thead th { text-align:center; background:grey; color:white}
</style>
</head>
<body>

<table border="1">
<thead >
<th>Title</th>
<th>No. Of Test</th>
</thead>
<tbody>
<tr>
<td>Total</td>
<td bgcolor="grey"><xsl:value-of select="testng-results/@total"></xsl:value-of></td>
</tr>
<tr>
<td>Passed</td>
<td bgcolor="green"><xsl:value-of select="testng-results/@passed"></xsl:value-of></td>
</tr>
<tr>
<td>Failed</td>
<td bgcolor="red"><xsl:value-of select="testng-results/@failed"></xsl:value-of></td>
</tr>
<tr>
<td>Skiped</td>
<td bgcolor="grey"><xsl:value-of select="testng-results/@skipped"></xsl:value-of></td>
</tr>
</tbody>
</table>
<span>Total Execution Time: <xsl:value-of select="round(testng-results/suite/@duration-ms div 1000 div 60)"></xsl:value-of> Minuts</span>

</body>
</html>

</xsl:template>


</xsl:stylesheet>