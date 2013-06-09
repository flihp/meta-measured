# bootable image with tboot

LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420 \
    "

IMAGE_INSTALL += "packagegroup-tboot"

INITRD_IMAGE ?= "core-image-tpm-initramfs"
INITRD = "${DEPLOY_DIR_IMAGE}/${INITRD_IMAGE}-${MACHINE}.cpio.gz"

ROOTFS_IMAGE ?= "core-image-tpm"
ROOTFS = "${DEPLOY_DIR_IMAGE}/${ROOTFS_IMAGE}-${MACHINE}.ext3"

NOHDIMG = "1"

# be sure the bootimg is built after the initrd and rootfs
do_bootimg[depends] += "${INITRD_IMAGE}:do_rootfs"
do_bootimg[depends] += "${ROOTFS_IMAGE}:do_rootfs"

inherit core-image
inherit bootimg

syslinux_iso_populate_append() {
	install -m 0444 ${STAGING_LIBDIR}/syslinux/mboot.c32 ${ISODIR}${ISOLINUXDIR}
}

# have bootimg populate function grab tboot and ACM
populate_append() {
	install -m 0644 ${DEPLOY_DIR_IMAGE}/tboot-${MACHINE}.gz ${DEST}/tboot.gz
	install -m 0644 ${DEPLOY_DIR_IMAGE}/acm_*.bin ${DEST}/
}

build_syslinux_cfg () {
	echo "ALLOWOPTIONS 1" > ${SYSLINUXCFG}
	echo "DEFAULT boot" >> ${SYSLINUXCFG}
	echo "TIMEOUT 10" >> ${SYSLINUXCFG}
	echo "PROMPT 1" >> ${SYSLINUXCFG}
	echo "LABEL boot" >> ${SYSLINUXCFG}
	echo "  KERNEL mboot.c32" >> ${SYSLINUXCFG}
	echo "  APPEND /tboot.gz logging=serial,vga,memory --- /vmlinuz ramdisk_size=32768 root=/dev/ram0 rw rootimg=rootfs.img rootimgpcr=9 console=tty0 console=ttyS0,115200n8 --- /initrd --- /acm_snb.bin --- /acm_ivb.bin" >> ${SYSLINUXCFG}
}
