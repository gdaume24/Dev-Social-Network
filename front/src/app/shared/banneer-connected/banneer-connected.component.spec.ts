import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BanneerConnectedComponent } from './banneer-connected.component';

describe('BanneerConnectedComponent', () => {
  let component: BanneerConnectedComponent;
  let fixture: ComponentFixture<BanneerConnectedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BanneerConnectedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BanneerConnectedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
