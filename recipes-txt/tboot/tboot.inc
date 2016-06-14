SUMMARY = "Multi-boot module to perform measured / verified launch using Intel (R) Trusted Execution Technology (Intel(R) TXT)."
DESCRIPTION = " \
Trusted Boot (tboot) is an open source, pre-kernel/VMM module that uses Intel(R) Trusted Execution Technology (Intel(R) TXT) to perform a measured and verified launch of an OS kernel/VMM. \
"
HOMEPAGE = "http://sourceforge.net/projects/tboot/"
SECTION = "bootloaders"
PR = "r0"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=7730ab1e15a162ca347bcc1722486d89"
DEPENDS = "libtspi openssl zlib"
DEPENDS_class-native = "trousers-native openssl-native zlib-native"

SRC_URI += " \
    http://downloads.sourceforge.net/project/${BPN}/${BPN}/${BPN}-${PV}.tar.gz \
    file://tboot-cross-compile.patch \
    file://no-install-strip.patch \
    file://tboot-no-senter-on-error.diff \
"

COMPATIBLE_HOST = '(x86_64|i.86).*-(linux|freebsd.*)'

inherit deploy

EXTRA_OEMAKE = "-e MAKEFLAGS="

do_compile() {
	# keep the OE TARGET_ARCH from confusing tboots Makefiles
	if [ "${TARGET_ARCH}" != "x86_64" ]; then
		TMP_TARGET_ARCH="x86_32"
	else
		TMP_TARGET_ARCH="x86_64"
	fi
	oe_runmake SUBDIRS=tboot CC="${HOST_PREFIX}gcc ${TOOLCHAIN_OPTIONS}" CPP="${HOST_PREFIX}cpp ${TOOLCHAIN_OPTIONS}" LDFLAGS="" CFLAGS=""
	oe_runmake SUBDIRS="lcptools lcptools-v2 tb_polgen utils docs" CFLAGS+="-std=c99" TARGET_ARCH="${TMP_TARGET_ARCH}"
}

do_install() {
	oe_runmake DISTDIR="${D}${base_prefix}" install
}

do_deploy() {
	install -m 0644 ${D}/boot/tboot.gz ${DEPLOYDIR}/tboot-${MACHINE}.gz
}

do_deploy_class-native() {
	:
}

sysroot_stage_all_append_class-target() {
	sysroot_stage_dir ${D}/boot ${SYSROOT_DESTDIR}/kernel
}

addtask deploy after do_install before do_build

BBCLASSEXTEND = "native"

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
