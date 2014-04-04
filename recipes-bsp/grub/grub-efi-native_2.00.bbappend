# GRUB_MODS_BUILTIN_append = " multiboot2 relocator acpi gzio"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://unicode.pf2;md5=d15b4716ae6d5a63bb93cadd1dfe8a13 \
"

do_deploy_append () {
	# echo
	install -m 0644 ${B}/grub-core/echo.mod ${DEPLOYDIR}
	# terminal stuffs
	install -m 0644 ${B}/grub-core/terminal.mod ${DEPLOYDIR}
	install -m 0644 ${B}/grub-core/terminfo.mod ${DEPLOYDIR}
	# hand gfx data to kernel?
	install -m 0644 ${B}/grub-core/efi_gop.mod ${DEPLOYDIR}
	install -m 0644 ${B}/grub-core/efi_uga.mod ${DEPLOYDIR}
	# gfx stuffs
	install -m 0644 ${B}/grub-core/gfxterm.mod ${DEPLOYDIR}
	install -m 0644 ${B}/grub-core/bitmap_scale.mod ${DEPLOYDIR}
	install -m 0644 ${B}/grub-core/gfxmenu.mod ${DEPLOYDIR}
	install -m 0644 ${B}/grub-core/trig.mod ${DEPLOYDIR}
	install -m 0644 ${B}/grub-core/font.mod ${DEPLOYDIR}
	# multiboot2
	install -m 0644 ${B}/grub-core/multiboot2.mod ${DEPLOYDIR}
	install -m 0644 ${B}/grub-core/relocator.mod ${DEPLOYDIR}
	install -m 0644 ${B}/grub-core/acpi.mod ${DEPLOYDIR}
	# gzip for tboot.gz
	install -m 0644 ${B}/grub-core/gzio.mod ${DEPLOYDIR}
	# serial
	install -m 0644 ${B}/grub-core/serial.mod ${DEPLOYDIR}
	# font
	install -m 0644 ${WORKDIR}/unicode.pf2 ${DEPLOYDIR}
}
