# bootable image with tboot
BBPATH += ""

LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420 \
    "

IMAGE_INSTALL += "base-passwd packagegroup-tboot"

INITRD_IMAGE ?= "core-image-tpm-initramfs"
INITRD = "${DEPLOY_DIR_IMAGE}/${INITRD_IMAGE}-${MACHINE}.cpio.gz"

ROOTFS_IMAGE ?= "core-image-tpm"
ROOTFS = "${DEPLOY_DIR_IMAGE}/${ROOTFS_IMAGE}-${MACHINE}.ext3"

NOHDD = "1"

# be sure the bootimg is built after the initrd and rootfs
do_bootimg[depends] += "${INITRD_IMAGE}:do_rootfs"
do_bootimg[depends] += "${ROOTFS_IMAGE}:do_rootfs"

inherit core-image
inherit bootimg

syslinux_hddimg_populate_append() {
	install -m 0444 ${STAGING_DATADIR}/syslinux/libcom32.c32 $hdd_dir${SYSLINUXDIR}
	install -m 0444 ${STAGING_DATADIR}/syslinux/mboot.c32 $hdd_dir${SYSLINUXDIR}
}

syslinux_iso_populate_append() {
	install -m 0444 ${STAGING_DATADIR}/syslinux/libcom32.c32 $iso_dir${ISOLINUXDIR}
	install -m 0444 ${STAGING_DATADIR}/syslinux/mboot.c32 $iso_dir${ISOLINUXDIR}
}

grubefi_populate_append () {
	MOD_DIR="${DEST}${EFIDIR}/${BUILD_ARCH}-efi"
	install -d 0755 ${MOD_DIR}
	install -m 0644 ${DEPLOY_DIR_IMAGE}/multiboot2.mod ${MOD_DIR}
	install -m 0644 ${DEPLOY_DIR_IMAGE}/relocator.mod ${MOD_DIR}
	install -m 0644 ${DEPLOY_DIR_IMAGE}/acpi.mod ${MOD_DIR}
	install -m 0644 ${DEPLOY_DIR_IMAGE}/gzio.mod ${MOD_DIR}
}

grubefi_iso_populate() {
	iso_dir=$1
	grubefi_populate $iso_dir
	# Build a EFI directory to create efi.img
	mkdir -p ${EFIIMGDIR}/${EFIDIR}
	cp -dr $iso_dir/${EFIDIR}/* ${EFIIMGDIR}${EFIDIR}
	cp $iso_dir/vmlinuz ${EFIIMGDIR}
	echo "${GRUB_IMAGE}" > ${EFIIMGDIR}/startup.nsh
	if [ -f "$iso_dir/initrd" ] ; then
		cp $iso_dir/initrd ${EFIIMGDIR}
	fi
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

build_grub_cfg() {
	{
		echo "serial --unit=0 --speed=115200 --word=8 --parity=no --stop=1"
		echo "default=boot"
		echo "timeout=10"
		echo ""
		echo "insmod multiboot2"
		echo "insmod relocator"
		echo "insmod gzio"
		echo ""
		echo "menuentry 'boot'{"
		echo "multiboot2 /tboot.gz loglvl=all logging=serial,vga,memory vga_delay=10 loglvl=all"
		echo "module2 /vmlinuz ramdisk_size=32768 root=/dev/ram0 rw rootimg=rootfs.img rootimgpcr=9 console=tty0 console=ttyS0,115200n8"
		echo "module2 /initrd"
		echo "module2 /acm_ivb.bin"
		echo "}"
	} > ${GRUBCFG}
}
