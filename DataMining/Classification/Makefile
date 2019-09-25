all: run

run:
	python3 A2.py -b -p 1 -ns 1.1; \
	python3 A2.py -b -p 2 -ns 10; \
	python3 A2.py -b -p 1 -f -ns 1.1; \
	python3 A2.py -b -p 2 -f -ns 10; \
	python3 A2.py -c -p 2 --mci-data data/alzheimers_mci_test.csv; \
	python3 A2.py -b -p 1 --train-data data/sdsstrain.csv -ns 1.1; \
	python3 A2.py -b -p 2 --train-data data/sdsstrain.csv -ns 10; \
	python3 A2.py -b -p 1 --train-data data/sdsstrain.csv -f -ns 20; \
	python3 A2.py -b -p 2 --train-data data/sdsstrain.csv -f -ns 20; \
	python3 A2.py -c -p 4 --train-data data/sdsstrain.csv --test-data data/sdsstest.csv

reqs:
	pip install --user -r requirements.txt

report:
	pdflatex Report && \
	bibtex Report && \
	pdflatex Report && \
	pdflatex Report
