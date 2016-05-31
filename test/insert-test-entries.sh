#!/bin/bash

# This is not meant to be used over a running Jool installation!
# (Notice the remodprobing.)

sudo modprobe -r jool
sudo modprobe jool disabled

sudo jool -4at --mark 1 192.0.2.1 11-14
sudo jool -4at --mark 2 192.0.2.2 11-14
sudo jool -4au --mark 1 192.0.2.1 10-15
sudo jool -4au --mark 2 192.0.2.1 10-15
sudo jool -4au --mark 2 192.0.2.2 10-15
sudo jool -4ai --mark 1 192.0.2.1 10-15
sudo jool -4ai --mark 2 192.0.2.2 10-15

sudo jool -bat 2001:db8::1#2000 192.0.2.1#11
sudo jool -bat 2001:db8::1#2001 192.0.2.1#12
sudo jool -bat 2001:db8::1#2002 192.0.2.1#13
sudo jool -bat 2001:db8::1#2003 192.0.2.2#14
sudo jool -bau 2001:db8::1#2005 192.0.2.1#10
sudo jool -bau 2001:db8::1#2006 192.0.2.1#11
sudo jool -bau 2001:db8::1#2007 192.0.2.1#12
sudo jool -bau 2001:db8::1#2008 192.0.2.1#13
sudo jool -bau 2001:db8::1#2009 192.0.2.1#14
sudo jool -bau 2001:db8::1#2010 192.0.2.1#15
sudo jool -bau 2001:db8::1#2011 192.0.2.2#12
sudo jool -bau 2001:db8::1#2012 192.0.2.2#14
sudo jool -bai 2001:db8::1#2013 192.0.2.1#12

sudo jool -4at 192.0.2.5 13
sudo jool -bat 2001:db8::1#2004 192.0.2.5#13
sudo jool -4rt 192.0.2.5 13 --quick

