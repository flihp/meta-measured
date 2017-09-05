OVERRIDES_append = "${bb.utils.contains('DISTRO_FLAGS', 'ima', ':ima-flag', '', d)}"

do_install_append_ima-flag () {
	if grep -v securityfs ${D}${sysconfdir}/fstab ; then
		echo "securityfs           /sys/kernel/security securityfs defaults              0  0" >> ${D}${sysconfdir}/fstab
	fi
}
