from setuptools import setup, find_packages

setup(
    name='apiverve_dnssecchecker',
    version='1.1.14',
    packages=find_packages(),
    include_package_data=True,
    install_requires=[
        'requests',
        'setuptools'
    ],
    description='DNSSEC Checker validates the DNSSEC (Domain Name System Security Extensions) configuration of a domain. It verifies that DNS responses are authenticated and haven't been tampered with.',
    author='APIVerve',
    author_email='hello@apiverve.com',
    url='https://apiverve.com/marketplace/dnsseccheck?utm_source=pypi&utm_medium=homepage',
    classifiers=[
        'Programming Language :: Python :: 3',
        'Operating System :: OS Independent',
    ],
    python_requires='>=3.6',
)
