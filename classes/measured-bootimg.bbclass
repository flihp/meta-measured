DEPENDS += " \
    tboot \
    tboot-native \
"

# be sure the bootimg is built after the initrd and rootfs
do_bootimg[depends] += "${INITRD_IMAGE}:do_rootfs"
do_bootimg[depends] += "${ROOTFS_IMAGE}:do_rootfs"

inherit core-image
inherit bootimg

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
}

build_syslinux_cfg () {
    sed -e "s&@TBOOT_CMDLINE@&${TBOOT_CMDLINE}&" \
        -e "s&@KERNEL_CMDLINE@&${KERNEL_CMDLINE}&" \
        ${WORKDIR}/syslinux.cfg > ${SYSLINUXCFG}
}

build_efi_cfg() {
    sed -e "s&@TBOOT_CMDLINE@&${TBOOT_CMDLINE}&" \
        -e "s&@KERNEL_CMDLINE@&${KERNEL_CMDLINE}&" \
        ${WORKDIR}/grub.cfg > ${GRUBCFG}
}
do_mlehash() {
    if [ ! "${NOHDD}" = "1" ]; then 
        lcp_mlehash -c "${TBOOT_CMDLINE}" ${HDDDIR}/tboot.gz > ${HDDDIR}/mlehash
    fi
    if [ ! "${NOISO}" = "1" ]; then
        lcp_mlehash -c "${TBOOT_CMDLINE}" ${ISODIR}/tboot.gz > ${ISODIR}/mlehash
    fi
}

addtask mlehash after do_bootimg before do_build

