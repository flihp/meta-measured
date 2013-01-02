# bootable image with tboot

LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420 \
    "

IMAGE_INSTALL = "packagegroup-tboot"

# syslinux
SYSLINUX_TIMEOUT = "10"
SYSLINUX_LABEL = "boot"
SYSLINUX_KERNEL_APPEND = "ramdisk_size=32768 root=/dev/ram0 rw console=tty0 console=ttyS0,115200n8"
SYSLINUX_TBOOT_APPEND = "logging=serial,vga,memory"

# images
INITRD_IMAGE = "core-image-tpm-initramfs"
INITRD = "${DEPLOY_DIR_IMAGE}/${INITRD_IMAGE}-${MACHINE}.cpio.gz"
NOISO = "1"

# HDDDIMG = "${S}/hddimg"

do_bootimg[depends] += "${INITRD_IMAGE}:do_rootfs"

inherit core-image
inherit bootimg

# have syslinux populate function install mboot.c32 for multiboot
syslinux_populate_append() {
	install -m 0444 ${STAGING_LIBDIR}/syslinux/mboot.c32 ${HDDDIR}${SYSLINUXDIR}/mboot.c32
}

# have bootimg populate function grab tboot and ACM
populate_append() {
	install -m 0644 ${DEPLOY_DIR_IMAGE}/tboot-${MACHINE}.gz ${DEST}/tboot.gz
	install -m 0644 ${DEPLOY_DIR_IMAGE}/acm_snb.bin ${DEST}/acm_snb.bin
}

# syslinux.bbclass can't deal with mboot.c32 configs
build_syslinux_cfg() {
	echo ALLOWOPTIONS 1 > ${SYSLINUXCFG}
	echo SERIAL 0 115200 > ${SYSLINUXCFG}
	echo DEFAULT ${SYSLINUX_LABEL} >> ${SYSLINUXCFG}
	echo TIMEOUT ${SYSLINUX_TIMEOUT} >> ${SYSLINUXCFG}
	echo PROMPT 1 >> ${SYSLINUXCFG}
	echo LABEL ${SYSLINUX_LABEL} >> ${SYSLINUXCFG}
	echo KERNEL mboot.c32 >> ${SYSLINUXCFG}
	echo APPEND tboot.gz ${SYSLINUX_TBOOT_APPEND} --- vmlinuz ${SYSLINUX_KERNEL_APPEND} --- initrd --- acm_snb.bin >> ${SYSLINUXCFG}
}
