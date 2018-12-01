#include <iostream>
#include <cstdlib>
#include <unordered_set>

int* memoize_changes(int argc, char* argv[]) {
  int* changes = (int*) malloc(sizeof(int) * (argc - 1));
  for(int i = 1; i < argc; ++i) {
    changes[i - 1] = atoi(argv[i]);
  }
  return changes;
}

int find_final_frequency(int num_changes, int* changes) {
  int final_frequency = 0;
  for(int i = 0; i < num_changes; ++i) {
    final_frequency += changes[i];
  }
  return final_frequency;
}

long find_first_double(int num_changes, int* changes) {
  long current_frequency = 0;
  std::unordered_set<int>* occured_frequencies = new std::unordered_set<int>();
  while(1) {
    for(int i = 0; i < num_changes - 1; ++i) {
      current_frequency += changes[i];
      if(occured_frequencies->find(current_frequency) != occured_frequencies->end()) {
        delete(occured_frequencies);
        return current_frequency;
      }
      occured_frequencies->insert(current_frequency);
    }
  }
}

int main(int argc, char* argv[]) {
  int* changes = memoize_changes(argc, argv);
  std::cout << "The final frequency is " << find_final_frequency(argc, changes) << std::endl;
  std::cout << "The first frequency that occurs twice is " << find_first_double(argc, changes) << std::endl;
  free(changes);
  return 0;
}
