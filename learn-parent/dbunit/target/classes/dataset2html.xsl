<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" media-type="text/xhtml" indent="yes" encoding="UTF-8"/>

    <xsl:template match="/dataset">
        <html>
            <head>
                <title>DB-Unit Dataset</title>
                <style>
                    body {
                    font-family: "Helvetica Neue", Helvetica, Arial;
                    font-size: 14px;
                    line-height: 20px;
                    font-weight: 400;
                    color: #3b3b3b;
                    -webkit-font-smoothing: antialiased;
                    font-smoothing: antialiased;
                    background: #2b2b2b;
                    }

                    .wrapper {
                    margin: 0 auto;
                    padding: 40px;
                    <!--max-width: 800px;-->
                    }

                    .table {
                    margin: 0 0 40px 0;
                    width: 100%;
                    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
                    display: table;
                    }
                    @media screen and (max-width: 580px) {
                    .table {
                    display: block;
                    }
                    }

                    .row {
                    display: table-row;
                    background: #f6f6f6;
                    }
                    .caption {
                    display: table-caption;
                    font-weight: 900;
                    color: #ffffff;
                    algin: middle;
                    background: #f6f6f6;
                    }
                    .row:nth-of-type(odd) {
                    background: #e9e9e9;
                    }
                    .row.header {
                    font-weight: 900;
                    color: #ffffff;
                    background: #ea6153;
                    }
                    .row.green {
                    background: #27ae60;
                    }
                    .row.blue {
                    background: #2980b9;
                    }
                    .caption.blue {
                    background: #2980b9;
                    }

                    .title{
                        color: #ffffff;
                        font-size: 20px;
                    }

                    @media screen and (max-width: 580px) {
                    .row {
                    padding: 8px 0;
                    display: block;
                    }
                    }

                    .cell {
                    padding: 6px 12px;
                    display: table-cell;
                    }
                    @media screen and (max-width: 580px) {
                    .cell {
                    padding: 2px 12px;
                    display: block;
                    }
                    }
                </style>
            </head>
            <body>
                <div class="title" id="top">Contents of H2 Database</div>
                <div class="wrapper">
                    <xsl:call-template name="print-tables"/>
                </div>
            </body>

        </html>
    </xsl:template>

    <xsl:template name="print-tables">
        <xsl:for-each select="./*">
            <xsl:if test="count(attribute::*) > 0">
                <xsl:if test="local-name(.) != local-name(preceding-sibling::*[1])">
                    <xsl:call-template name="toc">
                        <xsl:with-param name="tableName" select="local-name(.)"/>
                    </xsl:call-template>
                    <xsl:call-template name="table">
                        <xsl:with-param name="tableName" select="local-name(.)"/>
                    </xsl:call-template>
                    <p/>
                </xsl:if>
            </xsl:if>
        </xsl:for-each>

    </xsl:template>

    <xsl:template name="table">
        <xsl:param name="tableName"/>
        <div class="table" id="$tableName">
            <div class="caption blue"><xsl:value-of select="local-name(.)"/> - <a href="#top">top</a></div>
            <div class="row header blue">
                <xsl:for-each select="./attribute::*">
                    <div class="cell">
                        <xsl:value-of select="local-name(.)"/>
                    </div>
                </xsl:for-each>
            </div>
            <div class="row">
                <xsl:for-each select="./attribute::*">
                    <div class="cell">
                        <xsl:value-of select="."/>
                    </div>
                </xsl:for-each>
            </div>
            <xsl:for-each select="following-sibling::*[local-name() = $tableName]">
                <div class="row">
                    <xsl:for-each select="./attribute::*">
                        <div class="cell">
                            <xsl:value-of select="."/>
                        </div>
                    </xsl:for-each>
                </div>
            </xsl:for-each>
        </div>
    </xsl:template>

    <xsl:template name="toc">
        <xsl:param name="tableName"/>
        <p><a href="#$tableName"><xsl:value-of select="$tableName"/></a></p>
    </xsl:template>

</xsl:stylesheet>