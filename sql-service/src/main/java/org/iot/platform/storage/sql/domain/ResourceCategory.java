package org.iot.platform.storage.sql.domain;

public enum ResourceCategory {
  COMMON_RESOURCES(0, 2047) {
    @Override
    public boolean isBelong(int resourceNo) {
      return resourceNo >= start && resourceNo <= end;
    }
  },
  REUSABLE_RESOURCES(2048, 26240) {
    @Override
    public boolean isBelong(int resourceNo) {
      return resourceNo >= start && resourceNo <= end;
    }
  },
  PRIVATE_RESOURCES(26241, 32768) {
    @Override
    public boolean isBelong(int resourceNo) {
      return resourceNo >= start && resourceNo <= end;
    }
  };

  int start;
  int end;

  ResourceCategory(int startIndex, int endIndex) {
    start = startIndex;
    end = endIndex;
  }

  public static ResourceCategory getCategory(int no){
    for (ResourceCategory c : ResourceCategory.values() ) {
        if(c.isBelong(no)){
          return c;
        }

    }
    return null;
  }

  public abstract boolean isBelong(int resourceNo);
}
