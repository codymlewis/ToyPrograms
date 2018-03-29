<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<!-- format-xslt.xsl
     Cody Lewis - c3283349
     seng1050 A02
     Description: Gives the style sheet formatting for the xml -->

	<xsl:output method="html"/>

	<xsl:template match="//cuisine">
		<html lang="en">
			<head>
				<meta charset="UTF-8"/>
				<meta name="author" content="Cody Lewis" />
				<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
				<link rel="stylesheet" type="text/css" href="style.css"/>
				<script async="" type="text/Javascript" charset="utf-8" src="back2TopBtn.js"></script>
				<title><xsl:value-of select="@type"/>
					restaurants</title>
				<style>
					/* The home button looked odd when referring to the global css */
					img.home {
						height: 6.5%;
						width:  4%;
						float:left;
						float:top;
					}
				</style>
			</head>
			<body>
				<header>
					<a href="index.html">
						<img src="images/home.svg" class="home" alt="home" />
					</a>
					<h1><xsl:value-of select="@type"/>
						Restaurants</h1>
					<nav>
						<hr/>
						<div>

							<xsl:choose>

								<xsl:when test="@type='Burger'">
									<a href="italian.xml">Italian</a>
									<xsl:text> </xsl:text>
								</xsl:when>

								<xsl:otherwise>
									<a href="burgers.xml">Burgers</a>
									<xsl:text> </xsl:text>
								</xsl:otherwise>
							</xsl:choose>
							<a href="contact.html">Contact us</a>
							<xsl:text> </xsl:text>
							<a href="about.html">About us</a>
							<xsl:text> </xsl:text>
							<a href="feedback.html">Send feedback</a>
						</div>
						<hr/>
					</nav>
				</header>

				<xsl:for-each select="restaurant">

					<xsl:sort select="name"/>
					<article>
						<div>
							<h2><xsl:value-of select="name"/></h2>
							<img class="logo">

								<xsl:attribute name="src">
									<xsl:value-of select="logo"/>
								</xsl:attribute>

								<xsl:attribute name="alt">
									<xsl:value-of select="name"/>
								</xsl:attribute>
							</img>
							<p><xsl:value-of select="description"/></p>
							<p>Located at

								<xsl:apply-templates select="address"/>
							</p>
							<p>Their phone number is

								<xsl:value-of select="phone"/></p>
							<p>And their website is
								<a>

									<xsl:attribute name="href">
										<xsl:value-of select="URL"/>
									</xsl:attribute>

									<xsl:value-of select="URL"/>
								</a>
							</p>
							<p>They're open on
								<br/>

								<xsl:apply-templates select="openingHours/date"/></p>

							<xsl:apply-templates select="delivery"/>

							<xsl:apply-templates select="picture"/>
						</div>
					</article>

				</xsl:for-each>
				<!-- The below line will be a back to top button with js -->
				<input type='image' src='./images/upArr.png' onclick='toTop()' id='topBtn' title='' alt='Back to Top' value=''></input>

				<footer>
					<address>
						Background image from
						<a href="http://www.nmgncp.com/white-wallpaper-abstract/6852008.html">nmgncp.com</a><br/>
						Copyright (c) 2017 Copyright Holder All Rights Reserved.
						<br/>
						<a href="./privacypolicy.html">Privacy Policy</a>
						<br/>
						<a href="./tac.html">Terms And Conditions</a>
						<br/>
						Cody Lewis
						<br/>
						SN: 3283349
						<br/>
						c3283349@uon.edu.au
						<br/>
						Australia
						<br/>
					</address>
				</footer>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="address">

		<xsl:value-of select="number"/>
		<xsl:text> </xsl:text>

		<xsl:value-of select="street"/>,
		<br/>

		<xsl:value-of select="suburb"/>
		<xsl:text>, </xsl:text>

		<xsl:value-of select="state"/>
		<xsl:text>, </xsl:text>

		<xsl:value-of select="postcode"/>,
		<br/>

		<xsl:value-of select="country"/>
	</xsl:template>

	<xsl:template match="openingHours/date">
		<strong><xsl:value-of select="@day"/></strong>
		<br/>
		<xsl:text>Opening time: </xsl:text>

		<xsl:value-of select="open"/>
		<br/>
		<xsl:text>Closing time: </xsl:text>

		<xsl:value-of select="close"/>
		<br/>
	</xsl:template>

	<xsl:template match="delivery">
		<p>The minimum price of food here is

			<xsl:value-of select="minimumPrice"/>
			with a delivery fee of

			<xsl:value-of select="fee"/></p>
	</xsl:template>

	<xsl:template match="picture">
		<img class="restaurant" style="float:right;">

			<xsl:attribute name="src">
				<xsl:value-of select="current()"/>
			</xsl:attribute>

			<xsl:attribute name="alt">
				<xsl:value-of select="current()"/>
			</xsl:attribute>
		</img>
	</xsl:template>

</xsl:stylesheet>
