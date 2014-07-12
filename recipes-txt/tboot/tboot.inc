SUMMARY = ""
DESCRIPTION = " \
  "
SECTION = "bootloaders"
PR = "r0"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=661cd71df9c2b05204f9fef109bb7ff5"
DEPENDS = "libtspi openssl zlib"

SRC_URI += " \
    http://downloads.sourceforge.net/project/${PN}/${PN}/${PN}-${PV}.tar.gz \
    file://tboot-cross-compile.patch \
    file://tboot-tb_polgen-params-print-type.patch \
    file://no-install-strip.patch \
    file://tboot-no-senter-on-error.diff \
"

COMPATIBLE_HOST = '(x86_64|i.86).*-(linux|freebsd.*)'

do_compile() {
	# keep the OE TARGET_ARCH from confusing tboots Makefiles
	if [ "${TARGET_ARCH}" != "x86_64" ]; then
		TMP_TARGET_ARCH="x86_32"
	else
		TMP_TARGET_ARCH="x86_64"
	fi
	oe_runmake SUBDIRS:=tboot CC="${HOST_PREFIX}gcc ${TOOLCHAIN_OPTIONS}" CPP="${HOST_PREFIX}cpp ${TOOLCHAIN_OPTIONS}" LDFLAGS="" CFLAGS=""
	oe_runmake SUBDIRS:="lcptools lcptools-v2 tb_polgen utils docs" CFLAGS+="-std=c99" TARGET_ARCH="${TMP_TARGET_ARCH}"
}

do_install() {
	oe_runmake DISTDIR="${D}" install
}

PACKAGES += " \
    ${PN}-grub \
    ${PN}-lcptools \
    ${PN}-lcptools-v2 \
    ${PN}-tbpolgen \
    ${PN}-utils \
"
FILES_${PN} = "${base_prefix}/boot/tboot.gz"
FILES_${PN}-dbg += "${base_prefix}/boot/tboot-syms"
FILES_${PN}-grub = "${sysconfdir}/grub.d"
FILES_${PN}-lcptools = " \
    ${sbindir}/lcp_crtpconf \
    ${sbindir}/lcp_crtpol \
    ${sbindir}/lcp_crtpol2 \
    ${sbindir}/lcp_crtpolelt \
    ${sbindir}/lcp_crtpollist \
    ${sbindir}/lcp_readpol \
    ${sbindir}/lcp_mlehash \
    ${sbindir}/lcp_writepol \
    ${sbindir}/tpmnv_defindex \
    ${sbindir}/tpmnv_getcap \
    ${sbindir}/tpmnv_lock \
    ${sbindir}/tpmnv_relindex \
"
FILES_${PN}-lcptools-v2 = " \
    ${sbindir}/lcp2_crtpol \
    ${sbindir}/lcp2_crtpolelt \
    ${sbindir}/lcp2_crtpollist \
    ${sbindir}/lcp2_mlehash \
"
FILES_${PN}-tbpolgen = "${sbindir}/tb_polgen"
FILES_${PN}-utils = " \
    ${sbindir}/acminfo \
    ${sbindir}/parse_err \
    ${sbindir}/txt-stat \
"

sysroot_stage_all_append() {
	sysroot_stage_dir ${D}/boot ${SYSROOT_DESTDIR}/kernel

	install -d ${DEPLOY_DIR_IMAGE}
	install -m 0644 ${D}/boot/tboot.gz ${DEPLOY_DIR_IMAGE}/tboot-${MACHINE}.gz
}
