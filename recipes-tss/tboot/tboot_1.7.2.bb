SUMMARY = ""
DESCRIPTION = " \
  "
SECTION = "bootloaders"
PR = "r0"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=661cd71df9c2b05204f9fef109bb7ff5"
DEPENDS = "libtspi openssl zlib"


SRC_URI += " \
    http://downloads.sourceforge.net/project/tboot/tboot/tboot-1.7.2.tar.gz \
    file://tboot-cross-compile.patch \
    file://tboot-tb_polgen-params-print-type.patch \
"

SRC_URI[md5sum] = "c8f981e5ee0a36a033e96383835c603b"
SRC_URI[sha256sum] = "e0ed44d7ff7d2fa018fbf7cb9dccfaee47269d9a9f4a4688824aaa32353ab323"

COMPATIBLE_HOST = '(x86_64|i.86).*-(linux|freebsd.*)'

do_compile() {
    # keep the OE TARGET_ARCH from confusing tboots Makefiles
    if [ "${TARGET_ARCH}" != "x86_64" ]; then
        TMP_TARGET_ARCH="x86_32"
    else
        TMP_TARGET_ARCH="x86_64"
    fi
    oe_runmake SUBDIRS:=tboot CPP="${HOST_PREFIX}cpp ${TOOLCHAIN_OPTIONS}" LDFLAGS="" CFLAGS=""
    oe_runmake SUBDIRS:="lcptools tb_polgen utils docs" CFLAGS+="-std=c99" TARGET_ARCH="${TMP_TARGET_ARCH}"
}

do_install() {
    oe_runmake DISTIR="${D}" DISTDIR="${D}" install
}

FILES_${PN}-dbg += "${base_prefix}/boot/tboot-syms"
