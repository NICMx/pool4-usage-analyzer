# pool4 Usage Analyzer

## Introduction

Reports the utilization of Jool's pool4 mark-protocol combinations. The point is to monitor this data to prevent exhaustion.

## Installation

You need Java 6 at least.

This repository holds the source code. You can download a precompiled binary from the [NICMx releases project](https://github.com/NICMx/releases/tree/master/Jool).

## Usage

First, locate your jool binary. You will need this info in the next step:

	$ locate jool
	/usr/local/bin/jool

Now allow yourself to execute `/usr/local/bin/jool --bib` without needing to enter a password.

	$ sudo visudo

Assuming your username is "potato", add `potato ALL=NOPASSWD: /usr/local/bin/jool` and save.

Test your configuration. Make sure Jool is modprobed and kicking:

	$ jool
	  Status: Enabled

Then open a new terminal (so your previous sudo password "times out") and make sure you're not prompted for a password when you run this:

	$ sudo jool -bn

You can finally try the analyzer: (Your output is going to be significantly more boring if pool4 hasn't been populated)

	$ java -jar pool4-usage-analyzer-<version>.jar
	Mark	Proto	Total	Used	Used %
	1	TCP	6	3	50
	2	TCP	6	1	16
	1	UDP	6	6	100
	2	UDP	12	8	66
	1	ICMP	6	1	16
	2	ICMP	6	0	0
	
	Orphan BIB entries: 1

That's all the jar does; you want to monitor this table.

One way to accomplish that is to create a cron job that will whine when one or more of the usage ratios are too high.

`/home/potato/check-jool.sh`:

	#!/bin/bash
	
	java -jar pool4-usage-analyzer-<version>.jar | while read line
	do
		usage=$(echo $line | awk '{ print $5 }') 
		if [[ "$usage" -gt 75 ]]
		then
			echo "pool4 usage is too high: $line"
		fi
	done

`crontab -e`:

	MAILTO="alert@example.mx"
	*/5 * * * * /home/potato/check-jool.sh > /dev/null 2>&1
