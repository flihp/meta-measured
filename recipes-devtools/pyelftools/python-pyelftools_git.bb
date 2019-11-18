inherit setuptools
require python-pyelftools.inc

RDEPENDS_${PN} = "python python-contextlib python-debugger python-pprint"
RDEPENDS_${PN}_class-native = "python"

