#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin
[ -z "$CONSOLE" ] && CONSOLE="/dev/console"

fatal() {
    echo $1 >$CONSOLE
    echo >$CONSOLE
    exec sh
}

early_setup () {
    mkdir /proc /sys
    mount -t proc proc /proc
    mount -t sysfs sysfs /sys

    mkdir /tmp /run
    ln -s /run /var/run
}

tss_setup () {
    modprobe -i tpm-tis
    # tcsd needs loopback networking
    echo "127.0.0.1	localhost" > /etc/hosts
    if ! ip addr add 127.0.0.1/8 dev lo ; then
	fatal "failed to give loopback a localhost address"
    fi
    if ! ip link set up dev lo ; then
	fatal "failed to bring up loopback device"
    fi
    # get trousers udev rules executed
    if ! /etc/init.d/udev start ; then
	fatal "failed to start udev"
    fi
    # run tcsd
    if ! /etc/init.d/trousers start ; then
	fatal "failed to start tcsd"
    fi
}

early_setup
tss_setup

exec sh
