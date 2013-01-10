# bootable image with tboot

LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420 \
    "

IMAGE_INSTALL = "packagegroup-tboot"

# syslinux top level config and labels
SYSLINUX_TIMEOUT = "10"
SYSLINUX_DEFAULT = "${SYSLINUX_LABEL_boot}"
SYSLINUX_MODULAR = "true"
SYSLINUX_LABELS = "boot"

# syslinux config for individual labels
SYSLINUX_LABEL_boot = "boot"
SYSLINUX_MODULES_boot = "tboot kernel initrd acmsnb acmivb"
SYSLINUX_MODULE_boot-tboot = "/tboot.gz"
SYSLINUX_MODULE_APPEND_boot-tboot = "logging=serial,vga,memory"
SYSLINUX_MODULE_boot-kernel = "/vmlinuz"
SYSLINUX_MODULE_APPEND_boot-kernel = "ramdisk_size=32768 root=/dev/ram0 rw rootimg=rootfs.img rootimgpcr=9 console=tty0 console=ttyS0,115200n8"
SYSLINUX_MODULE_boot-initrd = "/initrd"
SYSLINUX_MODULE_boot-acmsnb = "/acm_snb.bin"
SYSLINUX_MODULE_boot-acmivb = "/acm_ivb.bin"

INITRD_IMAGE = "core-image-tpm-initramfs"
INITRD = "${DEPLOY_DIR_IMAGE}/${INITRD_IMAGE}-${MACHINE}.cpio.gz"

ROOTFS_IMAGE = "core-image-tpm"
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

# prototype to build syslinux modular config
python build_syslinux_cfg () {
    cfile = d.getVar('SYSLINUXCFG', True)
    if not cfile:
        raise bb.build.FuncFailed('Unable to read SYSLINUXCFG')

    try:
        cfgfile = file(cfile, 'w')
    except OSError:
        raise bb.build.funcFailed('Unable to open %s' % (cfile))

    modular = d.getVar('SYSLINUX_MODULAR', True)
    if not modular:
         bb.error('syslinux config not modular, cannot configure')
         return

    # top level configs
    cfgfile.write('ALLOWOPTIONS 1\n');
    default = d.getVar('SYSLINUX_DEFAULT', True)
    if default:
        cfgfile.write('DEFAULT %s\n' % default)
    else:
        cfgfile.write('DEFAULT %s\n' % labels[0])

    timeout = d.getVar('SYSLINUX_TIMEOUT', True)
    if timeout:
        cfgfile.write('TIMEOUT %s\n' % timeout)
    else:
        cfgfile.write('TIMEOUT %s\n' % 50)

    prompt = d.getVar('SYSLINUX_PROMPT', True)
    if prompt:
        cfgfile.write('PROMPT %s\n' % prompt)
    else:
        cfgfile.write('PROMPT 1\n')

    # loop over labels in config: SYSLINUX_LABELS"
    labels = d.getVar('SYSLINUX_LABELS', True)
    if not labels:
        bb.error("no SYSLINUX_LABELS defined for config, fail")
        return
    if labels == []:
        bb.error("no LABLES in SYSLINUX_LABELS, fail");
        return
    for label in labels.split():
        bb.debug(1, 'got a label: %s\n' % label)
        labelval = d.getVar('SYSLINUX_LABEL_' + label, True)
        if not labelval:
            bb.error('no value for label %s' % label)
            return
        cfgfile.write('LABEL %s\n' % labelval)
        cfgfile.write('  KERNEL mboot.c32\n')
        cfgfile.write('  APPEND ')

        # loop over modules in label: SYSLINUX_MODULE_label-module
        modules = d.getVar('SYSLINUX_MODULES_' + label, True)
        if not modules:
            bb.error('No modules specified for label: %s, fail' % label)
            return
        if modules == []:
            bb.error('No modules in list for lable: %s, fail' % label)
            return
        for module in modules.split():
            modval = d.getVar('SYSLINUX_MODULE_' + label + '-' + module)
            cfgfile.write('%s' % modval)
            # get 'APPEND' variable for passing command line to module
            #   SYSLINUX_MODULE_APPEND_label-module
            append = d.getVar('SYSLINUX_MODULE_APPEND_' + label + '-' + module)
            if append:
                cfgfile.write(' %s' % append)
            # if not last module, append connector thing
            if module != modules.split()[-1]:
                cfgfile.write(' --- ')
            else:
                cfgfile.write('\n')

    return
}
