do_deploy_append () {
	install -m 0644 ${B}/grub-core/multiboot2.mod ${DEPLOYDIR}
	install -m 0644 ${B}/grub-core/relocator.mod ${DEPLOYDIR}
	install -m 0644 ${B}/grub-core/acpi.mod ${DEPLOYDIR}
	install -m 0644 ${B}/grub-core/gzio.mod ${DEPLOYDIR}
}
