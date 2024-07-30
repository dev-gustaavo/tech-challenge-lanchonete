#!/bin/bash
for i in {1..100000}; do
  curl localhost:32000/cliente
done