# bootable image with tboot
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420 \
    "

IMAGE_INSTALL += "base-passwd packagegroup-tboot"

INITRD_IMAGE_LIVE ?= "core-image-tpm-initramfs"
INITRD_LIVE = "${DEPLOY_DIR_IMAGE}/${INITRD_IMAGE_LIVE}-${MACHINE}.cpio.gz"

ROOTFS_IMAGE ?= "core-image-tpm"
ROOTFS = "${DEPLOY_DIR_IMAGE}/${ROOTFS_IMAGE}-${MACHINE}.ext3"

NOHDD = "1"

TBOOT_CMDLINE = "loglvl=all logging=serial,vga,memory"
KERNEL_CMDLINE = "ramdisk_size=32768 root=/dev/ram0 ro measureroot rootimg=rootfs.img rootimgpcr=9 console=tty0 console=ttyS0,115200n8"

inherit measured-bootimg
