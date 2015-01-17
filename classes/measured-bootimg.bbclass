DEPENDS += "tboot-native"

do_mlehash() {
    if [ ! "${NOHDD}" = "1" ]; then 
        lcp_mlehash -c "${TBOOT_CMDLINE}" ${HDDDIR}/tboot.gz > ${HDDDIR}/mlehash
    fi
    if [ ! "${NOISO}" = "1" ]; then
        lcp_mlehash -c "${TBOOT_CMDLINE}" ${ISODIR}/tboot.gz > ${ISODIR}/mlehash
    fi
}

addtask mlehash after do_bootimg before do_build

