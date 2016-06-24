DEPENDS += " \
    tboot \
    tboot-native \
    3rd-gen-i5-i7-sinit \
    4th-gen-i5-i7-sinit \
    5th-gen-i5-i7-sinit \
"

# be sure the bootimg is built after the initrd and rootfs
do_bootimg[depends] += "${INITRD_IMAGE_LIVE}:do_rootfs"
do_bootimg[depends] += "${ROOTFS_IMAGE}:do_rootfs"

inherit core-image

# fetch/unpack tasks don't normally run for image recipes. This means our
# bootloader configs won't end up in WORKDIR unless we do some magic here.
python () {
	# Ensure we run these usually noexec tasks
	d.delVarFlag("do_fetch", "noexec")
	d.delVarFlag("do_unpack", "noexec")
}

syslinux_hddimg_populate_append() {
	install -m 0444 ${STAGING_DATADIR}/syslinux/libcom32.c32 $hdd_dir${SYSLINUXDIR}
	install -m 0444 ${STAGING_DATADIR}/syslinux/mboot.c32 $hdd_dir${SYSLINUXDIR}
}

syslinux_iso_populate_append() {
	install -m 0444 ${STAGING_DATADIR}/syslinux/libcom32.c32 $iso_dir${ISOLINUXDIR}
	install -m 0444 ${STAGING_DATADIR}/syslinux/mboot.c32 $iso_dir${ISOLINUXDIR}
}

# have bootimg populate function grab tboot and ACM
populate_append() {
	install -m 0644 ${DEPLOY_DIR_IMAGE}/tboot-${MACHINE}.gz ${DEST}/tboot.gz
	install -m 0644 ${DEPLOY_DIR_IMAGE}/acm_*.bin ${DEST}/
        lcp_mlehash -c "${TBOOT_CMDLINE}" ${DEST}/tboot.gz > ${DEST}/mlehash
}

build_syslinux_cfg () {
    cat > ${SYSLINUX_CFG} << EOF
ALLOWOPTIONS 1
DEFAULT boot
TIMEOUT 10
PROMPT 1
LABEL boot
  KERNEL mboot.c32
  APPEND /tboot.gz ${TBOOT_CMDLINE} --- /vmlinuz ${KERNEL_CMDLINE} --- /initrd  --- /acm_ivb.bin --- /acm_hsw.bin
EOF
}

# this is uuuuugly
build_efi_cfg() {
    cat > ${GRUB_CFG} << EOF
serial --unit=0 --speed=115200 --word=8 --parity=no --stop=1
default=boot
timeout=10

terminal_input console serial
terminal_output console serial
EOF

    echo "menuentry 'tboot' {" >> ${GRUB_CFG}

    cat >> ${GRUB_CFG} << EOF
  multiboot2 /tboot.gz ${TBOOT_CMDLINE}
  module2 /vmlinuz ${KERNEL_CMDLINE}
  module2 /initrd
  module2 /acm_ivb.bin
  module2 /acm_hsw.bin
EOF

    echo -e "}\nmenuentry 'not-tboot' {" >> ${GRUB_CFG}

    cat >> ${GRUB_CFG} << EOF
  linux /vmlinuz ${KERNEL_CMDLINE}
  initrd /initrd
EOF
    echo "}" >> ${GRUB_CFG}
}
