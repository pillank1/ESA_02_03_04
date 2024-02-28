<?xml version="1.0" ?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="3.0"
>
    <xsl:output method="html" indent="yes" media-type="text/html" encoding="UTF-8" />
    <xsl:template match="/">
        <html>
            <head>
                <title>XSLT API</title>
                <body>
                    <xsl:apply-templates />
                </body>
            </head>
            <style>
                table, th{
                border: 1px solid black;
                background: blue;
                color: white;
                }
                td {
                border: 1px solid black;
                background: green;
                color: white;
                }
            </style>
        </html>
    </xsl:template>
    <xsl:template match="ArrayList">
        <h1>Heroes</h1>
        <table>
            <tr>
                <th>id</th>
                <th>Name Hero</th>
                <th>Level</th>
                <th>Class Hero</th>
                <th>Spell Cells</th>
            </tr>
            <xsl:for-each select="item">
                <tr>
                    <td><xsl:value-of select="id"/></td>
                    <td><xsl:value-of select="nameHero"/></td>
                    <td><xsl:value-of select="level"/></td>
                    <td><xsl:value-of select="classHero"/></td>
                    <td><xsl:value-of select="spellCells"/></td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>